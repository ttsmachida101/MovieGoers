# MovieGoers-READ ME

## About
MovieGoers is mobile application which allows users to view screening movies, their screening times, screening locations
and allows them to select seats and buy tickets online. The application allows movie goers to also buy concessions in advance and simply pick them up uopn arrival.

## Overview
  Our submission file consists of 3 packages, two of them which are test packages. 
  Within our main implementation package we have the main folders persistence,Business(new), presentation, logs, objects and business. 

  The persistence folder contains a mock database(updated to include concessions and event) of all the movies within our system(Events). The reason why we re-named this "Events" is to allow for re-usability and scalability in the event that we expand from movie tickets to concerts, shows and so on. Within the mock database we also have a list of the locationas available for viewing movies (theatres). Persistance also allows injection such that it can be made from DataAccessor interface. Each theatre has a screen associated with it. Each theatre also has a specific set of concessions associated with it. 
  
  The logs file contains the project logs which contains all of our logging information.
  The presentation folder contains our GUI objects for display. We have added a few new screens to our interface 
  Our access file contains our accessors to the appropriately named  titles.
  Within our objects folder we have 5 main object classes which are Customer, Concessions, Theater, Movie, Event and Ticket.. 
  Movie - the movie object represents a particular movie. It takes in the title, Id of a movie, genre, actors, duration as strings and     also the poster for the associated movie.
   _new_ Event- the event object takes in a theater object (for the particular theater the movie is showing in, the corresponding movie     object the time and the seat capacity as literals) As you will notice the seats capcaity and seats available has been updated as a new   feature. This has replaced the ticket object we had implemented before.
  Theater - The theater object holds the theater information (name, location and logo) as literals.
  _new_ Concessions - The concessions object holds the particular theatre the concessions are available and the actual concessions as a     combo   for the sake of convenience
  _new_ Ticket - The ticket object is created when a user makes a purchase to keep track of the movies they've purchased.
  Users are able to register if they would like to have their information readily available and to added a little bit of customization     to their app.
  There is also a profile feature available for users where they can view a history of what they have purchased and choose a display       picture to add a little bit of customization to their page.
  For those that don't want to bother with profiles and registration there is also a guest feature avaiable so that users are able to     quickly be able to make a purchase without all the extra hassle. 
  
  
## Features
  Upon startup, the application shows a splash screen with the app-logo and you're given the choice of going to your profile screen or go to the list of movies avaiable. The movies list screen contains what is the list of movies which are currently playing in various locations.
  Each movie title is accompanied by a (poster) and a rating for the movie. This is was one of our key tasks, for the user to be able 
  to see the list of movies playing. Upon selecting a movie or selecting view theaters, the theaters where the movie is playing are showing.
  Upon selecting a location, the user can view the times and seat availability of the particular location. After this the customer can view the available seats for a selected location. If there are seats available the customer can book a seatm if not he will be notified of the full capacity and requested to select another screen(new). After booking a seat the customer can now pick some concessions from a list of carefully selected combinations of concessions (new). After picking these, the customer can proceed to pay for their bill(new). They will have to enter their credit card information and then from there they can view a summary of their ticket and the summary.  Another key developer task 
  which we implemented.
  
 ## Authors
  Tino Machida, KJ, Joe and Erin
  
  
