Once project is opened in eclipse, run the project. 
1: An option pane will appear prompting the user to choice the desired number of players from a drop down menu. If the pane
is closed or the cancel button is pressed, the program will end. Otherwise, if OK is selected, whatever number last shown
in the drop down menu will be the number of players entered in the game. 
2: Once OK is selected in the select number of players pane, another pane will pop up prompting the user to choice
a game piece for the first player. By default the first piece is highlighted, so if the window is closed, the first piece is
assigned to the player. These panes will continue to appear for each player.
3: Once all players have selected a game piece, the game board frame will display. 
	To play the game, press the roll button. 
	A pane will appear displaying the results of two die roll. When that window is closed or OK is pressed, a new pane will 
	display the square the user has landed on (which based on the sum of the two die).
		-Case 1: Landing on a safe square or the start square: A pane informing the user that they have landed on a safe 
		square will show.
		-Case 2: Landing an un-owned property: A pane displaying the name of the property, how much it costs,
			the rent other players will have to pay when they land on the purchased property, and an offer to purchase the
			property will appear. 
				-If the user declines the offer, the property remains without an owner and there is no change
				to the bank balance or the players balance.
				-If the user accepts the offer but the player cannot afford the property a pane stating that they cannot
				afford the property appears and the result is the same as declining the offer.
				-If the user accepts the offer and the player can afford the property, the cost of the property is deducted
				from the player's balance and added to the bank balance, the property is then owned by that player, and their
				number will appear on the square as long as they own it. 
		-Case 3: Landing on a square owned by the current player: The player will have the option to upgrade the property.
			A pane will appear with the offer to upgrade the property, the cost of the upgrade, and the rent after upgrading.
				-If the user declines the offer, the property remains without that upgrade.
				-If the user accepts but the player cannot afford the upgrade, the property remains without that upgrade.
				-If the user accepts and the player can afford it, the upgrade cost is deducted from the player's balance
				and added to the bank balance, the upgrade cost for the next upgrade is increased, and rent is increased.
		-Case 4: Landing on a square that is owned by another player: A pane stating who owns the property and how much rent 
			will be paid to the owner pops up. When the pane is closed or OK is pressed the current player then automatically
			has the rent amount deducted from their balance and added to the owner's balance.
			-If paying rent causes the current player to have a balance of zero then the current player is out of the game.
			-If paying rent cannot afford to pay rent, they pay their remaining balance, and they are out of the game.
			*Once a player is out of the game by losing all his/her money, his/her properties go back on the market
				for an increased price. 
		-Case 5: Landing on a draw card square: There are two different card piles, one for each draw card square. When a 
			player lands on one of the squares, they will be prompted to press OK to draw a card. A pane will then display
			a message informing the player that they must pay money or get money for some reason. Upon pressing OK, the 
			appropriate amount will either be added or deducted from their balance (and possibly deducted or added to the
			bank balance depending on the card).
4: If the user selects forfeit, a pane will display asking if they are sure they want to forfeit. If the OK button is pressed
the current player will be removed from the game. Their remaining balance will be given to the bank and their balance will 
become zero, all their properties will go back on the market for an increased price, and for the remainder of the game their
turn will be skipped over.
5: Once all but one player have a balance of zero, the player with a balance greater than zero is the winner. A pane stating
that they are the winner will appear, and upon closing the pane or clicking yes, the program will terminate. 

*At any point in the game, if the game board frame is closed, the program will terminate. 
				