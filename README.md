# 411_Password_Manager

### How to run program from a clone of the GitHub repo

1. Open up the folder in your IDE of choice (the one we will be using as for this tutorial is IntelliJ)
2. Open the src/sample folder, and right click Main to run the file.
    1. You will likely get an error stating [Error: JavaFX Runtime components are missing, and are required to run this application]
3. If/when you see this, click the Run tab at the top left of the window, or your screen if you're on a Mac, and click Edit Configurations
4. Once the popup opens, click the "Modify Options" button near the top right of the "Build and Run" section, and click "Add VM options".
5. After that, add in the following options to the VM Options field `--module-path /add/user/path/javafx-sdk-17.0.1/lib --add-modules javafx.controls,javafx.fxml`
    1. /add/user/path/ is the path from your drive's root directory to the location of /lib under /javafx-sdk-17.0.1. You can obtain this by going to terminal, cd-ing to the javafx-sdk-17.01/lib folder, then running the `pwd` command', then copying and pasting the path outputted to the VM options field.
    2. For example, in my case the path will be `/Users/myusername/work/new_411/411_Password_Manager/javafx-sdk-17.0.1/lib`
        1. So for example, my VM options will be something like `--module-path /Users/myusername/work/new_411/411_Password_Manager/javafx-sdk-17.0.1/lib --add-modules javafx.controls,javafx.fxml`
6. After adding these VM options, click the Ok button to the bottom right, and run your run configuration that should run the Main class in src/sample.
7. If all settings are correct, an application window for our Password Manager should appear!
