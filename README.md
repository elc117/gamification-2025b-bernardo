1. Click on 'Code', select 'Codespaces' and click '+' to create a Codespace on master

2. In the terminal of the recently created Codespace:

    i. Update the Java version

    ```bash
    sdk install java 17.0.8-tem
    sdk default java 17.0.8-tem
    ```

    ii. Build the HTML project

    ```bash
    ./gradlew html:dist
    ```

    iii. Run the HTML project

    ```bash
    cd html/build/dist
    python -m http.server
    ```
