# Mobiquity Android Task

Brief Overview

# Language
- Kotlin

## Architecture

Android Model-View-ViewModel (MVVM) design architecture was used with the Repository
pattern. This is to prevent tight-coupling of components (allowing for proper
separation of concerns) thereby favoring testability, easier code maintenance
and extensibility as the code base grows.

The project package was structured by `Feature`. This is to aid readability,
code navigation & modularity.

# Libraries Used
- `Dagger 2` - For Dependency Injection
- `Retrofit & RxJava` - For Making Network request
- `Glide` - For Image Loading
- `Mockito` - For mocking dependencies during `unit test`

## Tests
This app was built with testability in mind.

- The `ViewModel`, `Repository` and `DataHelper` classes used in this task were unit tested with `Mockito`.
  Dependencies have been injected where possible to be substituted with Mocks.

- Espresso was also used to Test the project view files
- The kotlin Extension function used in this project was also unit-tested

## Miscellaneous
- List of category data was obtained from `http://mobcategories.s3-website-eu-west-1.amazonaws.com`