package com.github.bitstachio.listdemo.activities;

import java.util.ArrayList;

public class MockDatabase {

    private static final ArrayList<ListModel> players = new ArrayList<>();

    static {
        players.add(new ListModel("Robert Sánchez", "Goalkeeper", "Spanish goalkeeper known for his commanding presence and shot-stopping ability. Joined Chelsea from Brighton in 2023.", 0));
        players.add(new ListModel("Marc Cucurella", "Left Back", "Versatile Spanish defender with pace and crossing ability. Signed from Brighton in 2022.", 0));
        players.add(new ListModel("Tosin Adarabioyo", "Centre-Back", "English centre-back with strong aerial presence and ball-playing skills. Joined from Fulham in 2023.", 0));
        players.add(new ListModel("Benoît Badiashile", "Centre-Back", "French defender known for his composure and tackling. Signed from AS Monaco in 2023.", 0));
        players.add(new ListModel("Levi Colwill", "Centre-Back", "English youth product with excellent positioning and passing range. Promoted from Chelsea's academy.", 0));
        players.add(new ListModel("Jorrel Hato", "Left Back", "Dutch full-back with attacking flair and defensive solidity. Joined from Ajax in 2025.", 0));
        players.add(new ListModel("Reece James", "Right Back", "English right-back known for his crossing and leadership. Chelsea academy graduate.", 0));
        players.add(new ListModel("Moisés Caicedo", "Defensive Midfielder", "Ecuadorian midfielder with relentless energy and defensive prowess. Joined from Brighton in 2023.", 0));
        players.add(new ListModel("Enzo Fernández", "Central Midfielder", "Argentine playmaker with vision and creativity. Signed from Benfica in 2023.", 0));
        players.add(new ListModel("Andrey Santos", "Central Midfielder", "Brazilian midfielder with box-to-box capabilities. Joined from Vasco da Gama in 2023.", 0));
        players.add(new ListModel("Cole Palmer", "Attacking Midfielder", "English attacking midfielder with dribbling skills and goal-scoring ability. Joined from Manchester City in 2023.", 0));
        players.add(new ListModel("Estêvão", "Attacking Midfielder", "Brazilian teenager with exceptional dribbling and playmaking abilities. Signed from Palmeiras in 2025.", 0));
        players.add(new ListModel("Alejandro Garnacho", "Winger", "Argentine winger known for his pace and flair. Joined from Manchester United in 2025.", 0));
        players.add(new ListModel("Jamie Gittens", "Winger", "English winger with flair and speed. Joined from Borussia Dortmund in 2025.", 0));
        players.add(new ListModel("Liam Delap", "Striker", "English striker with physical presence and goal-scoring instincts. Joined from Ipswich Town in 2025.", 0));
    }

    public static ArrayList<ListModel> getPlayers() {
        return players;
    }
}
