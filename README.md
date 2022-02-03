<h1 align="center">
    <a href="https://kotlin-inquirer.github.io/kotlin-inquirer/">
        <img src="https://raw.githubusercontent.com/kotlin-inquirer/kotlin-inquirer/master/assets/kotlin-inquirer-logo.png" width="40%"/>
    </a>
</h1>

[![Build Status](https://travis-ci.com/kotlin-inquirer/kotlin-inquirer.svg?branch=master)](https://travis-ci.org/kotlin-inquirer/kotlin-inquirer)
[![Apache License V.2](https://img.shields.io/badge/license-Apache%20V.2-blue.svg)](https://github.com/kotlin-inquirer/kotlin-inquirer/blob/master/LICENSE) 
[![](https://jitpack.io/v/kotlin-inquirer/kotlin-inquirer.svg)](https://jitpack.io/#kotlin-inquirer/kotlin-inquirer)

> A collection of common interactive command line user interfaces written in [![Pure Kotlin](https://img.shields.io/badge/100%25-kotlin-blue.svg)](https://kotlinlang.org/) inspired by [Inquirer.js](https://github.com/SBoudrias/Inquirer.js "Inquirer.js") 

<p align="center">
  <img src="https://raw.githubusercontent.com/kotlin-inquirer/kotlin-inquirer/master/assets/pizza.gif?raw=true" width="80%"/>
</p>

## :rocket: Run Demo Using [kscript](https://github.com/holgerbrandl/kscript "kscript")
Remote scriplet [raw-URL](https://raw.githubusercontent.com/kotlin-inquirer/kotlin-inquirer/master/scripts/src/pizza.kts 
"pizza.kts") 


```
kscript https://git.io/fjj2P
```

Or clone it

```
git clone https://github.com/kotlin-inquirer/kotlin-inquirer.git
cd kotlin-inquirer
kscript ./scripts/src/pizza.kts
```


## :cloud: Download

### Gradle
```groovy
allprojects {
  repositories {
    maven { url 'https://jitpack.io' }
  }
}
```

```groovy
dependencies {
  implementation 'com.github.kotlin-inquirer:kotlin-inquirer:v0.0.2-alpha'
}
```

## :clipboard: Usages

#### Confirm

```kotlin
val isDelivery: Boolean = KInquirer.promptConfirm("Is this for delivery?", default = false)
println("Is Delivery: $isDelivery")
```
<p align="center">
  <img src="https://raw.githubusercontent.com/kotlin-inquirer/kotlin-inquirer/master/assets/confirm-component.gif?raw=true" width="80%"/>
</p>

------

#### Input
```kotlin
val comments: String = KInquirer.promptInput("Any comments on your purchase experience?")
println("Comments: $comments")
```
<p align="center">
  <img src="https://raw.githubusercontent.com/kotlin-inquirer/kotlin-inquirer/master/assets/input-component.gif?raw=true" width="80%"/>
</p>

------

#### Input Numbers
```kotlin
val quantity: BigDecimal = KInquirer.promptInputNumber("How many do you need?")
println("Quantity: $quantity")
```
<p align="center">
  <img src="https://raw.githubusercontent.com/kotlin-inquirer/kotlin-inquirer/master/assets/input-number-component.gif?raw=true" width="80%"/>
</p>

------

#### Input Password
```kotlin
val password: String = KInquirer.promptInputPassword("Enter Your Password:", hint = "password")
println("Password: $password")
```
<p align="center">
  <img src="https://raw.githubusercontent.com/kotlin-inquirer/kotlin-inquirer/master/assets/input-password-component.gif?raw=true" width="80%"/>
</p>


------

#### List
```kotlin
val size: String = KInquirer.promptList("What size do you need?", listOf("Large", "Medium", "Small"))
println("Size: $size")
```
<p align="center">
  <img src="https://raw.githubusercontent.com/kotlin-inquirer/kotlin-inquirer/master/assets/list-component.gif?raw=true" width="80%"/>
</p>

------

#### List Multi Selection
```kotlin
val size: String = KInquirer.promptList("What size do you need?", listOf("Large", "Medium", "Small"))
println("Size: $size")
```
<p align="center">
  <img src="https://raw.githubusercontent.com/kotlin-inquirer/kotlin-inquirer/master/assets/list-multi-component.gif?raw=true" width="80%"/>
</p>

------

### :crystal_ball: Roadmap
#### Components
- [x] Confirm
- [x] Input
- [x] Input Numbers
- [x] Input Password
- [x] List
- [x] List with multi selection
- [ ] Input validation error message
- [ ] Support Hint
- [ ] Better package name

#### Operation
- [ ] Maven Central
- [ ] JCenter
- [x] GIFs
- [x] Example
- [x] Travis-CI
- [ ] codecov 
- [x] Logo


