News Application 

News application built using Jetpack compose for UI, kotlin coroutines and flow async operations, Retrofit and okhttp libraries for network calls, Coil for async image loading, Hilt for dependency injection, Junit, mockk, corutinesTest as testing libraries. Turbine can be used for testing coroutine flows 

This application is based on clean architecture principles.  

 

Module structure is as follows 

Module structure 

 

- business 

    - common 

        - domain 

            - models (does not have suffix) 

        - data 

            - persistence 

              - di 

              - main 

              - models (persistence ***Entity: PageEntity) 

              - api 

            - network 

              - di 

              - main (Retrofit instance) 

              - models (network ***Net: PageNet) 

              - api 

    - <<business-context>> (e.g. newsfeed, authentication) 

        - domain 

            - di 

            - main (usecase implementations (no interfaces), repo interface) 

            - models 

        - data (all submodules will include a di module, except for main, which has its di module directly under data) 

            - di 

            - main (repo implementations, dto<->domain mappers) 

            - network (retrofit api interface, datasource implementation, possible mapper) 

              - di 

              - main  

              - api (data source interface, dto models) 

            - mediators (optional: only needed when we have secondary data sources. For example getting favorite ids from preferneces) 

              - di 

              - main 

              - api (datasource interface, dto models) 

- features 

    - newsfeed 

        - presentation (viewmodel, reducer, state, ui model mapper) 

           - models 

           - state 

             - di 

             - main 

           - viewmodel 

             - di 

             - main 

        - ui 

           - main

  - common 

    - components 

        - ui (composable functions for small view atoms: text, button, etc) 

            - compositions 

      - kotlin 

	- di 

	- main (Generic util classes) 

- navigation 

	- api(model classes for compose navigation) 

 	- global ( compose global navigation related logic)
	       - presentation 

		- main 

		- model 

		- state 

Note:  

All the above modules are not currently used in the news application.  

All modules will have their own di submodule 

Items Summary 

Business - it will contain all the necessary domain and data layers related to a certain part of the app. It can be considered as a grouping for all the business rules/behaviours of the app and it will contain submodules including data and domain layers for each “context”. It is driven by data and a business rules and not UI/UX of any specific application.  

Common - a module that contains domain and data layers that don’t clearly belong to any specific  business context and whose purpose is mainly to be used but several other business contextes within the parent Business shell module. We should aim to keep this module as tiny as possible, 

An example of some code that could be contained here: 

  

Domain: Models - contains generic domain models that are used across business contexts (e.g. ErrorState, etc.) 

  

Data: Persistence - contains setup and definition for the app’s local persistence DB used across other business contexts. 

  

NewsFeed- a module that will group the logic related to one particular functionality of the application. In this case, it deals with handling news feed related logic and data. 

  

Domain - all the business logic in a form of domain models, use cases, that relates to this part of the app (in this case to the newsfeed) should be placed here 

  

Main - includes concrete implementations of usecases (implementing the base UseCase interface), 

  

Models - as the name suggests, all the domain models related to this domain.  

  

Data - will include all the data layer stuff including data sources, repositories implementations 

  

Api - this is the module, under each data source type (network, persistence), which contains the data source interface that will be later injected used in repo’s implementation, as well as all the data level models 

  

Main - contains the implementation of the repository, as well all the mappers that may be needed in process of converting data to domain models  

  

Network - Implementation of network datasource as well as network library api, can contain mappers (network model ↔︎ data model) 

  

Persistence - Implementation of local storage datasource as well as local storage library api can contain mappers (local storage model ↔︎ data model) 

  

Features - This module will contain all the features, emphasising the user-facing presentation layer - for example News feed Page. While presentation will be defined here, it should use common domain layer as well as feature related Business items 

  

News feed- Groups presentation layer of one of the user facing features, in this case for example news feed page 

  

Presentation - here all the presentation related logic will be placed - ViewModels, Reducers, mappers. Here also State will be placed 

  

UI - all the android and compose views should be placed here, no compositions or components are allowed to be bound to a specific feature, they should either live in common or template-engine. 

  

Presentation - Here we would define all of our factories that would decide which component or page to use. 

  

UI - This is where the routers would go 

   

Common - This is a container for all UI components, themes, and functionalities that don’t belong to a specific feature and whose main purpose is to be consumed by multiple features 

  

Components -  contains atomic components used by compositions (e.g. buttons, typography etc) 

  

Compositions - contains more complex composable assembling smaller components defined in the module above. Compositions can’t include other compositions. 

  

Navigation - contains all UI and presentation layers for app navigation, this includes navigation viewmodels, UI models, composables for compose routing, etc. 

 

**How to run the app **

- Checkout the code from this repo  

- Run debug variant 

This application is built using android studio HedgeHog, Java 17 environment 

Wrote unit test cases for Mapper, repository and use case classes 

Ex: ArticlesMapperTest, NewsFeedRepositoryImplTest 

 
