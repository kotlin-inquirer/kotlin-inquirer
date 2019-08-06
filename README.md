# Kotlin-Inquirer

 A common interactive command line user interfaces written in `Kotlin` inspired by [Inquirer.js](https://github.com/SBoudrias/Inquirer.js "Inquirer.js")



## Usages

#### Confirm
Free text user input
```kotlin
val isDelivery: Boolean = KInquirer.promptConfirm("Is this for delivery?", default = false)
```
<p align="center"><img src="/assets/confirm-component.gif?raw=true"/></p>
------

#### Input
Free text user input
```kotlin
val comments: String = KInquirer.promptInput("Any comments on your purchase experience?")
```
<p align="center"><img src="/assets/input-component.gif?raw=true"/></p>
------

#### Input Numbers
User input accepts only numbers
```kotlin
val quantity: BigDecimal = KInquirer.promptInputNumber("How many do you need?")
```
<p align="center"><img src="/assets/input-component.gif?raw=true"/></p>
------

#### Input Password
Password input with hidden value
```kotlin
val password: String = KInquirer.promptInputPassword("Enter Your Password:", hint = "password")
```
<p align="center"><img src="/assets/input-component.gif?raw=true"/></p>
------

#### List
List
```kotlin
val size: String = KInquirer.promptList("What size do you need?", listOf("Large", "Medium", "Small"))
```
<p align="center"><img src="/assets/input-component.gif?raw=true"/></p>
------

#### List Multi Selection
List with multiple selection
```kotlin
val size: String = KInquirer.promptList("What size do you need?", listOf("Large", "Medium", "Small"))
```
<p align="center"><img src="/assets/input-component.gif?raw=true"/></p>
------

#### Todo
##### Components
- [x] Confirm
- [x] Input
- [x] Input Numbers
- [x] Input Password
- [x] List
- [x] List with multi selection
- [ ] Input validation error message
- [ ] Support Hint

##### Operation
- [ ] Maven Central
- [ ] Gifs
- [ ] Travis-CI ?
- [ ] codecov 
- [ ] Icon
- [ ] Examples


