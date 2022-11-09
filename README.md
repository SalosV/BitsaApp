# BitsaApp


There are several things to consider when designing and developing an Android app. These include architecture, class hierarchy, package structure and the tech stack.

I approached this application with the following knowledge in mind:

- Multi-package apps
- Clean architecture
- MVVM
- Dependency injection with Dagger hilt
- Material design
- Style code



## Demo


![myfile](https://github.com/SalosV/BitsaApp/blob/main/Screenshot_20221109_034858.png)


## Tech Stack

- LiveData
- ViewModel
- Dagger hilt
- Lottie
- JUnit4
- Detekt

## Documentation

**Clean architecture**

Each of these entities is in charge of certain responsibilities, which are handled in isolation. All them are interconnected through interfaces, which allows you to achieve the necessary abstraction between them.

Here’s a bit about each layer:

- Presentation: This layer’s duties consist of managing events caused by user interactions and rendering the information coming from the domain layer. You’ll be using the well-known View, ViewModel (MVVM) architecture pattern. This entity “sees” the domain layer.
- Domain: This layer is in charge of the application business logic. It’s built upon use cases and repositories — see the Repository Pattern for more information. This entity only contains Kotlin code, so testing consists of unit tests. This layer represents the most inner entity, and thus it doesn’t “see” any layer other but itself.
- Data: This layer provides data to the application (data sources). You’ll be using Retrofit for service queries. This layer “sees” the domain layer.
Using clean architectures lets you make your code more SOLID. This makes applications more flexible and scalable when implementing new functionality, and it makes testing easier.
