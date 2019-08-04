# Kotlin-Inquirer

 A common interactive command line user interfaces in written in Kotlin inspired by [Inquirer.js](https://github.com/SBoudrias/Inquirer.js "Inquirer.js github repo")



## Components

#### Input
Free text user input
```kotlin
val fullName: String = KInquirer.promptInput("Enter Your Name:", hint = "(Full Name)")
```
<p align="center"><img src="/assets/input-component.gif?raw=true"/></p>

#### Input Numbers
User input accepts only numbers
```kotlin
val age: Int = KInquirer.promptInputNumber("Enter Your Age:", hint = "(only numbers)")
```
<p align="center"><img src="/assets/input-component.gif?raw=true"/></p>

#### Input Password
Password input with hidden value
```kotlin
val password: String = KInquirer.promptInputPassword("Enter Your Password:", hint = "(password)")
```
<p align="center"><img src="/assets/input-component.gif?raw=true"/></p>

#### List
List
```kotlin
val password: String = KInquirer.promptList("Select Stuff:", listOf("A", "B", "C"))
```
<p align="center"><img src="/assets/input-component.gif?raw=true"/></p>


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


