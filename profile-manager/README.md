# EECS 4443 - Lab 4

## App Theme

The app is a **Profile Picture Updater**, allowing users to either capture a new profile photo with the camera or select an existing one from the gallery.

## Architecture: Model-View-Controller (MVC)

This application is structured using the Model-View-Controller (MVC) architectural pattern.

*   **Model**: The data layer consists of the `ProfilePhoto` class, which holds the URI of the image, and the `ProfileRepository`, which manages saving and loading the photo's URI to and from `SharedPreferences`. This handles data persistence.
*   **View**: The `ProfileActivity` class and its corresponding XML layout file, `activity_profile.xml`, serve as the View. They are responsible for rendering the user interface, capturing user interactions (button clicks), and displaying the chosen image and status messages.
*   **Controller**: The `MainController` class acts as the controller. It mediates between the View and the Model, containing the core application logic. This includes managing runtime permissions, creating URIs for the camera, and processing the image URI received from the camera or gallery before passing it to the Model and View.

This separation of concerns makes the codebase more organized, scalable, and easier to maintain.

## Group Members and Contribution

*   **Barbod Habibi:** Implementing Model 
*   **Mehrshad Farahbakhsh:** Implementing View
*   **Amirhossein Mansour:** Implementing Controller
