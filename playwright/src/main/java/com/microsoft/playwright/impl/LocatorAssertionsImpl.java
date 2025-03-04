/*
 * Copyright (c) Microsoft Corporation.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.microsoft.playwright.impl;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.assertions.LocatorAssertions;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import static com.microsoft.playwright.impl.Serialization.serializeArgument;
import static com.microsoft.playwright.impl.Utils.convertType;

public class LocatorAssertionsImpl extends AssertionsBase implements LocatorAssertions {
  public LocatorAssertionsImpl(Locator locator) {
    this(locator, false);
  }

  private LocatorAssertionsImpl(Locator locator, boolean isNot) {
    super((LocatorImpl) locator, isNot);
  }

  @Override
  public void containsText(String text, ContainsTextOptions options) {
    ExpectedTextValue expected = new ExpectedTextValue();
    expected.string = text;
    expected.matchSubstring = true;
    expected.normalizeWhiteSpace = true;
    expectImpl("to.have.text", expected, text, "Locator expected to contain text", convertType(options, FrameExpectOptions.class));
  }

  @Override
  public void containsText(Pattern pattern, ContainsTextOptions options) {
    ExpectedTextValue expected = expectedRegex(pattern);
    expected.matchSubstring = true;
    expected.normalizeWhiteSpace = true;
    expectImpl("to.have.text", expected, pattern, "Locator expected to contain regex", convertType(options, FrameExpectOptions.class));
  }

  @Override
  public void containsText(String[] strings, ContainsTextOptions options) {
    List<ExpectedTextValue> list = new ArrayList<>();
    for (String text : strings) {
      ExpectedTextValue expected = new ExpectedTextValue();
      expected.string = text;
      expected.matchSubstring = true;
      expected.normalizeWhiteSpace = true;
      list.add(expected);
    }
    expectImpl("to.contain.text.array", list, strings, "Locator expected to contain text", convertType(options, FrameExpectOptions.class));
  }

  @Override
  public void containsText(Pattern[] patterns, ContainsTextOptions options) {
    List<ExpectedTextValue> list = new ArrayList<>();
    for (Pattern pattern : patterns) {
      ExpectedTextValue expected = expectedRegex(pattern);
      expected.matchSubstring = true;
      expected.normalizeWhiteSpace = true;
      list.add(expected);
    }
    expectImpl("to.contain.text.array", list, patterns, "Locator expected to contain text", convertType(options, FrameExpectOptions.class));
  }

  @Override
  public void hasAttribute(String name, String text, HasAttributeOptions options) {
    ExpectedTextValue expected = new ExpectedTextValue();
    expected.string = text;
    hasAttribute(name, expected, text, options);
  }

  @Override
  public void hasAttribute(String name, Pattern pattern, HasAttributeOptions options) {
    ExpectedTextValue expected = expectedRegex(pattern);
    hasAttribute(name, expected, pattern, options);
  }

  private void hasAttribute(String name, ExpectedTextValue expectedText, Object expectedValue, HasAttributeOptions options) {
    if (options == null) {
      options = new HasAttributeOptions();
    }
    FrameExpectOptions commonOptions = convertType(options, FrameExpectOptions.class);
    commonOptions.expressionArg = name;
    String message = "Locator expected to have attribute '" + name + "'";
    if (expectedValue instanceof Pattern) {
      message += " matching regex";
    }
    expectImpl("to.have.attribute", expectedText, expectedValue, message, commonOptions);
  }

  @Override
  public void hasClass(String text, HasClassOptions options) {
    ExpectedTextValue expected = new ExpectedTextValue();
    expected.string = text;
    expectImpl("to.have.class", expected, text, "Locator expected to have class", convertType(options, FrameExpectOptions.class));
  }

  @Override
  public void hasClass(Pattern pattern, HasClassOptions options) {
    ExpectedTextValue expected = expectedRegex(pattern);
    expectImpl("to.have.class", expected, pattern, "Locator expected to have class matching regex", convertType(options, FrameExpectOptions.class));
  }

  @Override
  public void hasClass(String[] strings, HasClassOptions options) {
    List<ExpectedTextValue> list = new ArrayList<>();
    for (String text : strings) {
      ExpectedTextValue expected = new ExpectedTextValue();
      expected.string = text;
      list.add(expected);
    }
    expectImpl("to.have.class.array", list, strings, "Locator expected to have class", convertType(options, FrameExpectOptions.class));
  }

  @Override
  public void hasClass(Pattern[] patterns, HasClassOptions options) {
    List<ExpectedTextValue> list = new ArrayList<>();
    for (Pattern pattern : patterns) {
      ExpectedTextValue expected = expectedRegex(pattern);
      list.add(expected);
    }
    expectImpl("to.have.class.array", list, patterns, "Locator expected to have class matching regex", convertType(options, FrameExpectOptions.class));
  }

  @Override
  public void hasCount(int count, HasCountOptions options) {
    if (options == null) {
      options = new HasCountOptions();
    }
    FrameExpectOptions commonOptions = convertType(options, FrameExpectOptions.class);
    commonOptions.expectedNumber = count;
    List<ExpectedTextValue> expectedText = null;
    expectImpl("to.have.count", expectedText, count, "Locator expected to have count", commonOptions);
  }

  @Override
  public void hasCSS(String name, String value, HasCSSOptions options) {
    ExpectedTextValue expected = new ExpectedTextValue();
    expected.string = value;
    hasCSS(name, expected, value, options);
  }

  @Override
  public void hasCSS(String name, Pattern pattern, HasCSSOptions options) {
    ExpectedTextValue expected = expectedRegex(pattern);
    hasCSS(name, expected, pattern, options);
  }

  private void hasCSS(String name, ExpectedTextValue expectedText, Object expectedValue, HasCSSOptions options) {
    if (options == null) {
      options = new HasCSSOptions();
    }
    FrameExpectOptions commonOptions = convertType(options, FrameExpectOptions.class);
    commonOptions.expressionArg = name;
    String message = "Locator expected to have CSS property '" + name + "'";
    if (expectedValue instanceof Pattern) {
      message += " matching regex";
    }
    expectImpl("to.have.css", expectedText, expectedValue, message, commonOptions);
  }

  @Override
  public void hasId(String id, HasIdOptions options) {
    ExpectedTextValue expected = new ExpectedTextValue();
    expected.string = id;
    expectImpl("to.have.id", expected, id, "Locator expected to have ID", convertType(options, FrameExpectOptions.class));
  }

  @Override
  public void hasId(Pattern pattern, HasIdOptions options) {
    ExpectedTextValue expected = expectedRegex(pattern);
    expectImpl("to.have.id", expected, pattern, "Locator expected to have ID matching regex", convertType(options, FrameExpectOptions.class));
  }

  @Override
  public void hasJSProperty(String name, Object value, HasJSPropertyOptions options) {
    if (options == null) {
      options = new HasJSPropertyOptions();
    }
    FrameExpectOptions commonOptions = convertType(options, FrameExpectOptions.class);
    commonOptions.expressionArg = name;
    commonOptions.expectedValue = serializeArgument(value);
    List<ExpectedTextValue> list = null;
    expectImpl("to.have.property", list, value, "Locator expected to have JavaScript property '" + name + "'", commonOptions);
  }

  @Override
  public void hasText(String text, HasTextOptions options) {
    ExpectedTextValue expected = new ExpectedTextValue();
    expected.string = text;
    expected.matchSubstring = false;
    expected.normalizeWhiteSpace = true;
    expectImpl("to.have.text", expected, text, "Locator expected to have text", convertType(options, FrameExpectOptions.class));
  }

  @Override
  public void hasText(Pattern pattern, HasTextOptions options) {
    ExpectedTextValue expected = expectedRegex(pattern);
    // Just match substring, same as containsText.
    expected.matchSubstring = true;
    expected.normalizeWhiteSpace = true;
    expectImpl("to.have.text", expected, pattern, "Locator expected to have text matching regex", convertType(options, FrameExpectOptions.class));
  }

  @Override
  public void hasText(String[] strings, HasTextOptions options) {
    List<ExpectedTextValue> list = new ArrayList<>();
    for (String text : strings) {
      ExpectedTextValue expected = new ExpectedTextValue();
      expected.string = text;
      expected.matchSubstring = false;
      expected.normalizeWhiteSpace = true;
      list.add(expected);
    }
    expectImpl("to.have.text.array", list, strings, "Locator expected to have text", convertType(options, FrameExpectOptions.class));
  }

  @Override
  public void hasText(Pattern[] patterns, HasTextOptions options) {
    List<ExpectedTextValue> list = new ArrayList<>();
    for (Pattern pattern : patterns) {
      ExpectedTextValue expected = expectedRegex(pattern);
      expected.matchSubstring = true;
      expected.normalizeWhiteSpace = true;
      list.add(expected);
    }
    expectImpl("to.have.text.array", list, patterns, "Locator expected to have text matching regex", convertType(options, FrameExpectOptions.class));
  }

  @Override
  public void hasValue(String value, HasValueOptions options) {
    ExpectedTextValue expected = new ExpectedTextValue();
    expected.string = value;
    expectImpl("to.have.value", expected, value, "Locator expected to have value", convertType(options, FrameExpectOptions.class));
  }

  @Override
  public void hasValue(Pattern pattern, HasValueOptions options) {
    ExpectedTextValue expected = expectedRegex(pattern);
    expectImpl("to.have.value", expected, pattern, "Locator expected to have value matching regex", convertType(options, FrameExpectOptions.class));
  }

  @Override
  public void isChecked(IsCheckedOptions options) {
    String expression = (options != null && options.checked != null && !options.checked) ? "to.be.unchecked" : "to.be.checked";
    expectTrue(expression, "Locator expected to be checked", convertType(options, FrameExpectOptions.class));
  }

  @Override
  public void isDisabled(IsDisabledOptions options) {
    expectTrue("to.be.disabled", "Locator expected to be disabled", convertType(options, FrameExpectOptions.class));
  }

  @Override
  public void isEditable(IsEditableOptions options) {
    expectTrue("to.be.editable", "Locator expected to be editable", convertType(options, FrameExpectOptions.class));
  }

  @Override
  public void isEmpty(IsEmptyOptions options) {
    expectTrue("to.be.empty", "Locator expected to be empty", convertType(options, FrameExpectOptions.class));
  }

  @Override
  public void isEnabled(IsEnabledOptions options) {
    expectTrue("to.be.enabled", "Locator expected to be enabled", convertType(options, FrameExpectOptions.class));
  }

  @Override
  public void isFocused(IsFocusedOptions options) {
    expectTrue("to.be.focused", "Locator expected to be focused", convertType(options, FrameExpectOptions.class));
  }

  @Override
  public void isHidden(IsHiddenOptions options) {
    expectTrue("to.be.hidden", "Locator expected to be hidden", convertType(options, FrameExpectOptions.class));
  }

  @Override
  public void isVisible(IsVisibleOptions options) {
    expectTrue("to.be.visible", "Locator expected to be visible", convertType(options, FrameExpectOptions.class));
  }

  private void expectTrue(String expression, String message, FrameExpectOptions options) {
    List<ExpectedTextValue> expectedText = null;
    expectImpl(expression, expectedText, null, message, options);
  }

  @Override
  public LocatorAssertions not() {
    return new LocatorAssertionsImpl(actualLocator, !isNot);
  }
}

