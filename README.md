# sales
# Created by Jonathas Duarte

Instructions

1) Configure the path for the gson-2.8.2.jar and junit4-4.8.2.jar in your buildpath.
2) Put the messages.json file in the path: C:\messages.json
3) The main method is in the class Program at sc.com.jpmorgan.sales.program folder.
4) There is a test class int the sc.com.jpmorgan.sales.test folder

The json file has the follwing format:
{
    "type":1,
    "product":"Apple",
    "value":10.0,
    "amount":1,
    "operation":""
},

Where
  "type" is the type of the message (1, 2 or 3),
  "product" is the name of the product
  "value" is the value of the product
  "amount" is the quantity of the product was sold
  "operation" is the name of an adjustment operation such as add, subtract or multiply

Example of messages type 1:
{
    "type":1,
    "product":"Apple",
    "value":10.0,
    "amount":1,
    "operation":""
}

Example of messages type 2:
{
    "type":2,
    "product":"Apple",
    "value":100.0,
    "amount":10,
    "operation":""
}

Example of messages type 3:
{
    "type":3,
    "product":"Apple",
    "value":20.0,
    "amount":0,
    "operation":"add"
}


