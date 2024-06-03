# [Team1] Design Document

## Instructions

*Save a copy of this template for your team in the same folder that contains
this template.*

*Replace italicized text (including this text!) with details of the design you
are proposing for your team project. (Your replacement text shouldn't be in
italics)*

*You should take a look at the example design document in the same folder as
this template for more guidance on the types of information to capture, and the
level of detail to aim for.*

## Financial app - Design

## 1. Problem Statement

Keep track of the daily to daily expenses and give analytics.
Instead of having to use a traditional app with icons, the user will have the faster option to type a text to 
interact with different features of the financial app. 

The user will just have to type a specific format of sentence that will trigger an action.
For example the user may type the following sentence : "Spend 30$ on McDonalds" (Or use the icons or boxes of the front end).
That suggests the app that the customer want to add to the daily expenses a record of the purchase of 30 dollars).


## 2. Top Questions to Resolve in Review

*List the most important questions you have about your design, or things that
you are still debating internally that you might like help working through.*

1.   Should we include the icons and the text options or only the text options?
2.   Should we add more complicated features or not?
3.  

## 3. Use Cases

    *This is where we work backwards from the customer and define what our customers
    would like to do (and why). You may also include use cases for yourselves, or
    for the organization providing the product to customers.*

U1. As a user, I want to add a new expense so that I can save my expense. 

U2. As a user, I want to save a new income so that I can save it.

U3. As a user I want to retrieve the list of the current month expenses so that I can make my own analytics.

U4.As user, I want to retrieve the list of the current month income so that I can make my mind about expenses.

U5. As user, I want to retrieve the list of the last month expenses so that I can make my own analytics.

U6. As user, I want to retrieve the list of the last month incomes  so that I can make my mind.

## 4. Project Scope

*Clarify which parts of the problem you intend to solve. It helps reviewers know
what questions to ask to make sure you are solving for what you say and stops
discussions from getting sidetracked by aspects you do not intend to handle in
your design.*

### 4.1. In Scope
*Add a new Expense
*Add a new income
*Retrieves the list of expenses
*Retrieves the list of incomes
    

### 4.2. Out of Scope

*Update an expense
*Update an income

# 5. Proposed Architecture Overview

This initial iteration will provide the minimum lovable product
(MLP) including creating, retrieving, an expense.
We will use API Gateway to create four endpoints (`CreateExpense`, `GetExpense`, `CreateIncome`, `GetIncome`).
We will store the expenses in a table called expenses. We will store incomes in a table called Income.

# 6. API

## 6.1. Public Models

```
//ExpenseModel

Integer amount;
String origin;
String time;

//IncomeModel

Integer amount;
String time;
String origin;

```

## 6.2. *Create Income endpoint*
* Accepts `POST` requests to `/incomes`
* Accepts data to create a new income with a provided amount,  time, and origin. Returns the new income.
* We will validate that the amount entered is not negative or equal to zero.
  * If the amount is not validated, we will throw an `InvalidAttributeException`.

    
## 6.3 *Get income endpoint*

* Accepts `GET` requests to `/incomes/:origin`
* Accepts an incomes/origin
    * If the given origin does not exist trows an `InvalidAttributeException`

*(repeat, but you can use shorthand here, indicating what is different, likely
primarily the data in/out and error conditions. If the sequence diagram is
nearly identical, you can say in a few words how it is the same/different from
the first endpoint)*

## 6.4. *Create Expense endpoint*
* Accepts `POST` requests to `/expenses`
* Accepts data to create a new expense with a provided amount,  time, and origin. Returns the new expense.
* We will validate that the amount entered is not negative or equal to zero.
  * If the amount is not validated, we will throw an `InvalidAttributeException`.


## 6.5 *Get expense endpoint*

* Accepts `GET` requests to `/expenses/:origin`
* Accepts an expenses/origin
  * If the given origin does not exist trows an `InvalidAttributeException`

*(repeat, but you can use shorthand here, indicating what is different, likely
primarily the data in/out and error conditions. If the sequence diagram is
nearly identical, you can say in a few words how it is the same/different from
the first endpoint)*

# 7. Tables

### 7.1. `incomes`

```
time // partition key, string
origin // String
amount // number

```

### 7.2. `expenses`

```
time // partition key, string
origin // String
amount // number

```


# 8. Pages

*Include mock-ups of the web pages you expect to build. These can be as
sophisticated as mockups/wireframes using drawing software, or as simple as
hand-drawn pictures that represent the key customer-facing components of the
pages. It should be clear what the interactions will be on the page, especially
where customers enter and submit data. You may want to accompany the mockups
with some description of behaviors of the page (e.g. “When customer submits the
submit-dog-photo button, the customer is sent to the doggie detail page”)*
