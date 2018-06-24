$(document).ready(function() {var formatter = new CucumberHTML.DOMFormatter($('.cucumber-report'));formatter.uri("./src/featureDefinition/Cucumber.feature");
formatter.feature({
  "comments": [
    {
      "line": 1,
      "value": "#Author: your.email@your.domain.com"
    },
    {
      "line": 2,
      "value": "#Keywords Summary :"
    },
    {
      "line": 3,
      "value": "#Feature: List of scenarios."
    },
    {
      "line": 4,
      "value": "#Scenario: Business rule through list of steps with arguments."
    },
    {
      "line": 5,
      "value": "#Given: Some precondition step"
    },
    {
      "line": 6,
      "value": "#When: Some key actions"
    },
    {
      "line": 7,
      "value": "#Then: To observe outcomes or validation"
    },
    {
      "line": 8,
      "value": "#And,But: To enumerate more Given,When,Then steps"
    },
    {
      "line": 9,
      "value": "#Scenario Outline: List of steps for data-driven as an Examples and \u003cplaceholder\u003e"
    },
    {
      "line": 10,
      "value": "#Examples: Container for s table"
    },
    {
      "line": 11,
      "value": "#Background: List of steps run before each of the scenarios"
    },
    {
      "line": 12,
      "value": "#\"\"\" (Doc Strings)"
    },
    {
      "line": 13,
      "value": "#| (Data Tables)"
    },
    {
      "line": 14,
      "value": "#@ (Tags/Labels):To group Scenarios"
    },
    {
      "line": 15,
      "value": "#\u003c\u003e (placeholder)"
    },
    {
      "line": 16,
      "value": "#\"\""
    },
    {
      "line": 17,
      "value": "## (Comments)"
    },
    {
      "line": 18,
      "value": "#Sample Feature Definition Template"
    }
  ],
  "line": 20,
  "name": "IMDB",
  "description": "I want to get the Top 250 Movies from IMDB",
  "id": "imdb",
  "keyword": "Feature",
  "tags": [
    {
      "line": 19,
      "name": "@tag"
    }
  ]
});
formatter.scenario({
  "line": 24,
  "name": "Fetch Top 250 Movies",
  "description": "",
  "id": "imdb;fetch-top-250-movies",
  "type": "scenario",
  "keyword": "Scenario",
  "tags": [
    {
      "line": 23,
      "name": "@Test"
    }
  ]
});
formatter.step({
  "line": 25,
  "name": "I have the IMDB url",
  "keyword": "Given "
});
formatter.step({
  "line": 26,
  "name": "I enter the IMDB url",
  "keyword": "When "
});
formatter.step({
  "line": 27,
  "name": "TOP IMDB movies is displayed",
  "keyword": "And "
});
formatter.step({
  "line": 28,
  "name": "fetch the Top movies and store it in database",
  "keyword": "Then "
});
formatter.match({
  "location": "FeatureDefinition.i_have_the_IMDB_url()"
});
formatter.result({
  "duration": 6453927595,
  "status": "passed"
});
formatter.match({
  "location": "FeatureDefinition.i_enter_the_IMDB_url()"
});
formatter.result({
  "duration": 8486944012,
  "status": "passed"
});
formatter.match({
  "location": "FeatureDefinition.top_IMDB_movies_is_displayed()"
});
formatter.result({
  "duration": 11101018335,
  "status": "passed"
});
formatter.match({
  "location": "FeatureDefinition.fetch_the_Top_movies_and_store_it_in_database()"
});
formatter.result({
  "duration": 39527998746,
  "status": "passed"
});
});