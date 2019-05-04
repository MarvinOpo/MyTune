# MyTune

A simple master-detail application that shows list of tracks from iTune API.

## Information
* Language: Java
* Architecture: Model-View-Presenter - I use MVP because im more used to its structure, and due to time constraints, i preferred using it.
* Ability to save data - Used GreenDao ORM to manage saving Tracks to local database. Shows local Tracks for display and if connected to the internet, the list will refresh and new data will be inserted/updated in the local DB. 
