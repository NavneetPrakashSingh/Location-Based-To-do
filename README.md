# Project Guidance

---

### 1. File Naming
##### 1.1 Class Files
Class names are always in UpperCamelCase.
For example, the **extends** in Android componets, should end with the name of the component. For example, UploadImageService, ChangePasswordDialog.

##### 1.2 Resource Files
Resource files should be written in lowercase_unserscore

##### 1.3 Layout Files

Layout files should match the name of the Android component that they are intended for, moving the top level component name at the begininig.

For example for an activity having class name with MainActivity, then the layout name for the clas will be activity_main.xml

Similary for a fragment that handles signin functionality having class SignUpFragment, we use fragment_sign_up.xml

Similary for dialogs, if we have a class name as ChangeDialogBox, we use dialog_change_password.xml.

However, in the case of a a list that is to be inflated with an Adapter, the layout should start with item_.

##### 1.3 Value Files

Resource files in the values folder should be **plural** e.g. strings.xml, colors.xml, styles.xml

---

### Coding Guidance

#### 2.1 Java langulage rules

##### 2.1.1 Don't ignore exception

You should never do this :

```java
try{
    someComplicatedFunctionThrowningSecurityError()
someComplicatedFunctionThrowingIOException()
}catch(Exception ex){
// catching all exceptions with generic handler
}
print s
```

##### 2.1.2 Use fully qualified imports
This is bad : **fruit.***

This is good : **fruit.apple**

---
#### 2.2 Java style rules
Fields should be at the top of any file and should follow the naming rules as listed below:

1. Private, and non-static fields start with **m**
2. Private static field start with **s**
3. Other fields start with lowercase
4. Static final fields (constants) are ALL_CAPS_WITH_UNDERSCORES


