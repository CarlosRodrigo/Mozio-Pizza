# Mozio Pizza Delivery App

The Mozio Pizza Delivery App has all the requirementes of the assignment.

## Architecture

The architecture of the app follows the best practices and recommended architecture for modern app development from the [android developer guide](https://developer.android.com/topic/architecture) using Clean Architecture in the Domain Layer following a simplified version of what has been [published](https://blog.cleancoder.com/uncle-bob/2012/08/13/the-clean-architecture.html) by Robert C Martin. This combination is also known as `MVVM with Clean Architecture`.

The overall architecture looks as shown on the image below:

<img src="https://developer.android.com/topic/libraries/architecture/images/mad-arch-overview.png" width="360" height="240">

The app has four main well-defined layers separated into packages

- **Presentation**:
  - The Presentation layer, also known as UI layer, is responsible for displaying the application data on the screen and also serve as the primary entry point for user interaction.
  - Inside the Presentation layer the app has the MainActivity which holds a NavHost that uses the Navigation library for adding Fragments and navigate through the app.
  - In this layer the app has the `Activities`, `Fragments` and its `ViewModels`.
- **Data**:
  - The Data layer contains the application data itself and connects to the Mozio Pizza test API.
  - Inside the Data layer the app has a Repository package that follows the `Repository Pattern` to fetch data. In this example data is fetched from the api which is accessed using the Retrofit library under the remote package. 
- **Domain**: 
  - The Domain Layer sits between the Presentation and Data layers and it is responsible for encapsulating the business logic of the application.
  - It is in this layer that the `UseCases` of the app will live. The `UseCases` are responsible for performing a business logic task
  - In here there is also a model package which contains the model classes of the app.
- **Core**: 
  - The core Layer contains some core classes that are used on the App.
  - There is the `UseCase` abstract class definition and signature that should be extended by every UseCase.
  - A `CustomException` for handling errors and a `State` class with three states (Loading, Success and Error).

Overall this architecture is very robust, follows the official android developer guideline and makes it easy to maintain, scale and test the application.

## Unit Tests

For unit testing the app currently only tests the UseCases which have the business logic of the application. 

For one of the tests it was necessary to `mock` the dependency of a repository in order to return mock data instead of actually fetching data from the server. The mock was done as a fake implementation that is provided as the dependency on the tests but it could also be done using any test mock library as well.

Also the business logic of the application that is implemented in the `GetPizzaOrderUseCase` is tested as well with all corner cases that were requested on the assignment.

## Instruction to build the project

This is a standard Android application, there are no special setup need. Just import the project into Android Studio and run the App.

## Demo

The following demo shows the app loading the flavors list from the JSON API and populating a RecyclerView with the flavor name, price and a checkbox to add flavors to the pizza.
When the user clicks on the confirm button it checks the business logic and if there are any errors (no flavor selected or more than two selected) a snack bar is shown otherwise it goes to the confirmation screen with the summary of the selected pizza and the final price.

<div style="display:flex">
     <div>
          <h3>Mozio Pizza Delivery App</h3>
          <img src="demo.gif" width="200"/>
     </div>
</div>

## Libraries used

The project was developed using some of the Android Jetpack Libraries and some third-party libraries as well.

### Android Jetpack Libraries
- View/Data Binding: Feature that allows to write code that interacts with views, replacing the `findViewById`. For more details see the [official docs](https://developer.android.com/topic/libraries/data-binding).
- Navigation Component: Using the idea of a Single Activity and multiple Fragments, the Navigation Component is used to navigate between the views of the app. For more details see the [official docs](https://developer.android.com/guide/navigation).

### Kotlin library:
- Kotlin coroutines: used to manage long-running tasks that might otherwise block the main thread. For more information see [The Kotlin coroutines official doc for Android](https://developer.android.com/kotlin/coroutines)

### Dependency Injection
- Koin: Library for dependency injection using a service locator in order to provide dependencies to the app. For more details see the [official docs](https://insert-koin.io/docs/quickstart/android)

### Third-party libraries:
- Retrofit + OkHttp: Http clients to simplify the communication with the [JSONPlaceholder API](https://jsonplaceholder.typicode.com/). [Official doc](https://square.github.io/retrofit/)
- Moshi: Used to deserialize the JSON API response into a Kotlin Data Class; [Official doc](https://github.com/square/moshi)
