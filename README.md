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

---
### Coding Guidance

