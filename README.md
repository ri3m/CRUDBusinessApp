# CRUDBusinessApp
Android CRUD operations app with a sqlite local database. Developed solely as a Mobile Application Development course project.
Users can sign up, login, and CRUD prodocts, as well as change the language of the app.

## Tools Used:
- Android Studio.
- SQLlite database.

## Interfaces:
### Login:
App launcher, new users can click on  create new account to transfer them to the signup activity.

<img width="308" alt="loginactivity" src="https://user-images.githubusercontent.com/60888719/90201461-a4c65f00-dde3-11ea-9d81-fecd78d5e1af.png">

### Signup:
The user must fill in all the fields, if one or more of the fields are left unfilled, or the email is already registered, or if the passwords don’t match a toast will appear informing the user of their error.
If the registration process is completed successfully, a toast will appear informing the user and then they can login through the log in activity.

<img width="306" alt="Signupactivity" src="https://user-images.githubusercontent.com/60888719/90201719-654c4280-dde4-11ea-9882-c4c00774f058.png">

### Main:
The main activity contains cards (CardView inside a RecyclerView) that consist of the products name, its description and its price. As well as two icons, to update the product or delete a product.
When the user clicks the delete icon, the product will be deleted and a toast informing the user that the process was completed successfully will appear.

<img width="306" alt="mainactivity" src="https://user-images.githubusercontent.com/60888719/90202128-7184cf80-dde5-11ea-8181-c1a404fb7b78.png">

### Update Product:
The user can update the products information by clicking the update icon available in the main activity for each product.
When the user clicks the icon, the fields are automatically filled with the old data, through which the user can edit.
If one or more of the fields are left unfilled, a toast will appear informing the user.
The price EditView only accepts numerical values to ensure data integrity.
If the update process is completed successfully, a toast will appear informing the user.

<img width="306" alt="updateactivity" src="https://user-images.githubusercontent.com/60888719/90202313-07205f00-dde6-11ea-8097-20170942b36c.png">


The side menu contains an option through which the user can change the app's language to Arabic or back to English.
