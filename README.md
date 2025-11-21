# jfx-vending-machine
In this repository is a JavaFX project simulating a vending machine (integrates the linked list data structure). Functionality includes buying, removing items from inventory, and checking the full list of items in inventory

# How does it work?
Machine is initialized with several snacks, which correspond to a filled TableView. The user can select options to buy snacks, remove snacks from purchased inventory, checking the full list of items in the inventory, and checkout/reset. Buying snacks and removing snacks from the purchased inventory updates the TableView (removing snacks from inventory adds item to table, while buying removes an item from the table). When the user selects to check out, the machine and corresponding tableview are both reset.
