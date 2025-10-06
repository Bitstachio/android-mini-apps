package com.github.bitstachio.listdemo.activities;

import com.github.bitstachio.listdemo.R;

import java.util.ArrayList;

public class MockDatabase {

    private static final ArrayList<Player> players = new ArrayList<>();

    static {
        players.add(new Player(0, "Robert Sánchez", R.drawable.thumb_robert_sanchez, R.drawable.img_robert_sanchez, "Goalkeeper", "Spanish goalkeeper known for his commanding presence and shot-stopping ability. Joined Chelsea from Brighton in 2023."));
        players.add(new Player(1, "Marc Cucurella", R.drawable.thumb_marc_cucurella, R.drawable.img_marc_cucurella, "Left Back", "Versatile Spanish defender with pace and crossing ability. Signed from Brighton in 2022."));
        players.add(new Player(2, "Tosin Adarabioyo", R.drawable.thumb_tosin_adarabioyo, R.drawable.img_tosin_adarabioyo, "Centre-Back", "English centre-back with strong aerial presence and ball-playing skills. Joined from Fulham in 2023."));
        players.add(new Player(3, "Benoît Badiashile", R.drawable.thumb_benoit_badiashile, R.drawable.img_benoit_badiashile, "Centre-Back", "French defender known for his composure and tackling. Signed from AS Monaco in 2023."));
        players.add(new Player(4, "Levi Colwill", R.drawable.thumb_levi_colwill, R.drawable.img_levi_colwill, "Centre-Back", "English youth product with excellent positioning and passing range. Promoted from Chelsea's academy."));
        players.add(new Player(5, "Jorrel Hato", R.drawable.thumb_jorrel_hato, R.drawable.img_jorrel_hato, "Left Back", "Dutch full-back with attacking flair and defensive solidity. Joined from Ajax in 2025."));
        players.add(new Player(6, "Reece James", R.drawable.thumb_reece_james, R.drawable.img_reece_james, "Right Back", "English right-back known for his crossing and leadership. Chelsea academy graduate."));
        players.add(new Player(7, "Moisés Caicedo", R.drawable.thumb_moises_caicedo, R.drawable.img_moises_caicedo, "Defensive Midfielder", "Ecuadorian midfielder with relentless energy and defensive prowess. Joined from Brighton in 2023."));
        players.add(new Player(8, "Enzo Fernández", R.drawable.thumb_enzo_fernandez, R.drawable.img_enzo_fernandez, "Central Midfielder", "Argentine playmaker with vision and creativity. Signed from Benfica in 2023."));
        players.add(new Player(9, "Andrey Santos", R.drawable.thumb_andrey_santos, R.drawable.img_andrey_santos, "Central Midfielder", "Brazilian midfielder with box-to-box capabilities. Joined from Vasco da Gama in 2023."));
        players.add(new Player(10, "Cole Palmer", R.drawable.thumb_cole_palmer, R.drawable.img_cole_palmer, "Attacking Midfielder", "English attacking midfielder with dribbling skills and goal-scoring ability. Joined from Manchester City in 2023."));
        players.add(new Player(11, "Estêvão", R.drawable.thumb_estevao, R.drawable.img_estevao, "Attacking Midfielder", "Brazilian teenager with exceptional dribbling and playmaking abilities. Signed from Palmeiras in 2025."));
        players.add(new Player(12, "Alejandro Garnacho", R.drawable.thumb_alejandro_garnacho, R.drawable.img_alejandro_garnacho, "Winger", "Argentine winger known for his pace and flair. Joined from Manchester United in 2025."));
        players.add(new Player(13, "Jamie Gittens", R.drawable.thumb_jamie_gittens, R.drawable.img_jamie_gittens, "Winger", "English winger with flair and speed. Joined from Borussia Dortmund in 2025."));
        players.add(new Player(14, "Liam Delap", R.drawable.thumb_liam_delap, R.drawable.img_liam_delap, "Striker", "English striker with physical presence and goal-scoring instincts. Joined from Ipswich Town in 2025."));
    }

    public static ArrayList<Player> getPlayers() {
        return players;
    }

    public static Player getPlayerById(int id) {
        for (Player player : players) {
            if (player.id == id) return player;
        }
        return null;
    }
}
