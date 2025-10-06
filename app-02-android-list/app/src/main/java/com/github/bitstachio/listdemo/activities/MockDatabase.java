package com.github.bitstachio.listdemo.activities;

import com.github.bitstachio.listdemo.R;

import java.util.ArrayList;

/**
 * A mock in-memory data source that provides sample {@link Player} objects.
 * <p>
 * This class simulates a simple database by preloading a static list of players,
 * each containing detailed information such as name, position, description, and image resources.
 * <p>
 * Used primarily for demonstration and UI testing within {@link ListActivity}
 * and {@link DetailsActivity}.
 */
public class MockDatabase {

    /**
     * Static list containing mock player data.
     */
    private static final ArrayList<Player> players = new ArrayList<>();

    // Populate the database with mock players when the class is loaded
    static {
        players.add(new Player(0, "Robert Sánchez", R.drawable.thumb_robert_sanchez, R.drawable.img_robert_sanchez, "Goalkeeper",
                "Robert Sánchez is a commanding Spanish goalkeeper renowned for his sharp reflexes, aerial control, and calm distribution from the back. After developing at Brighton & Hove Albion, he joined Chelsea in 2023 to solidify the club’s defensive line. Sánchez’s height and communication make him an essential organizer in set-piece situations, while his confidence with the ball fits perfectly into Chelsea’s possession-based system."));
        players.add(new Player(1, "Marc Cucurella", R.drawable.thumb_marc_cucurella, R.drawable.img_marc_cucurella, "Left Back",
                "Marc Cucurella is a tireless and energetic left-back known for his overlapping runs, pressing intensity, and quick transitions from defense to attack. Having started his career at Barcelona’s famed La Masia academy, he rose through La Liga before moving to Brighton and later Chelsea in 2022. Cucurella’s versatility allows him to operate both as a traditional full-back and as part of a back three, bringing flexibility and dynamism to Chelsea’s defensive setup."));
        players.add(new Player(2, "Tosin Adarabioyo", R.drawable.thumb_tosin_adarabioyo, R.drawable.img_tosin_adarabioyo, "Centre-Back",
                "Tosin Adarabioyo is an intelligent, composed English centre-back who combines strong aerial dominance with impressive ball-playing ability. A Manchester City academy graduate, Tosin joined Chelsea from Fulham in 2023, where his consistent performances in the Premier League earned widespread recognition. His height and composure make him an asset both defensively and in building play from the back, embodying the modern defender’s skill set."));
        players.add(new Player(3, "Benoît Badiashile", R.drawable.thumb_benoit_badiashile, R.drawable.img_benoit_badiashile, "Centre-Back",
                "Benoît Badiashile is a technically adept French defender who brings balance, calmness, and strong positional sense to Chelsea’s backline. Having emerged from AS Monaco’s youth system, he joined Chelsea in 2023 as one of Europe’s most promising young defenders. His ability to read the game and distribute accurately under pressure allows Chelsea to play fluidly from deep positions. Badiashile’s physical presence and tactical awareness make him a mainstay in both domestic and European fixtures."));
        players.add(new Player(4, "Levi Colwill", R.drawable.thumb_levi_colwill, R.drawable.img_levi_colwill, "Centre-Back",
                "Levi Colwill is a highly regarded English centre-back who embodies the modern defensive archetype: strong, composed, and progressive in possession. A product of Chelsea’s academy, Colwill’s maturity beyond his years has earned him regular first-team opportunities and international attention. Known for his intelligent positioning and left-footed balance in defense, he plays a crucial role in building attacks from the back while maintaining defensive solidity."));
        players.add(new Player(5, "Jorrel Hato", R.drawable.thumb_jorrel_hato, R.drawable.img_jorrel_hato, "Left Back",
                "Jorrel Hato is a dynamic Dutch full-back who blends defensive strength with attacking flair. Having made a name for himself at Ajax, his move to Chelsea in 2025 marked a major step in his young career. Hato’s pace and composure allow him to track back quickly while contributing to overlapping attacking sequences. His versatility enables him to play as a centre-back or left-back, making him one of Europe’s most exciting young defenders."));
        players.add(new Player(6, "Reece James", R.drawable.thumb_reece_james, R.drawable.img_reece_james, "Right Back",
                "Reece James, Chelsea’s captain and academy graduate, is widely regarded as one of the Premier League’s most complete right-backs. Renowned for his physical power, precise crossing, and tactical awareness, James contributes both defensively and offensively. His leadership on and off the pitch has made him an influential figure at Stamford Bridge. When fit, James provides width, creativity, and defensive resilience, embodying Chelsea’s tradition of strong homegrown talent."));
        players.add(new Player(7, "Moisés Caicedo", R.drawable.thumb_moises_caicedo, R.drawable.img_moises_caicedo, "Defensive Midfielder",
                "Moisés Caicedo is a high-energy Ecuadorian midfielder known for his relentless work rate, tactical intelligence, and ball-winning ability. After impressing at Brighton, he joined Chelsea in 2023 for a record transfer fee, quickly establishing himself as a key defensive shield in midfield. Caicedo’s blend of tenacity and technical ability makes him vital in controlling transitions and breaking opposition lines, anchoring Chelsea’s midfield with remarkable consistency."));
        players.add(new Player(8, "Enzo Fernández", R.drawable.thumb_enzo_fernandez, R.drawable.img_enzo_fernandez, "Central Midfielder",
                "Enzo Fernández is an Argentine midfield maestro recognized for his exceptional vision, passing range, and creativity. After winning the 2022 FIFA World Cup with Argentina and earning the tournament’s Best Young Player award, he joined Chelsea from Benfica in 2023. Enzo thrives as a deep-lying playmaker, orchestrating attacks and dictating tempo. His intelligence, composure, and leadership continue to make him one of Chelsea’s most technically gifted players."));
        players.add(new Player(9, "Andrey Santos", R.drawable.thumb_andrey_santos, R.drawable.img_andrey_santos, "Central Midfielder",
                "Andrey Santos is a promising Brazilian box-to-box midfielder who brings energy, vision, and attacking drive. Joining Chelsea from Vasco da Gama in 2023, Santos has quickly adapted to the pace of European football. His strong tackling, forward runs, and eye for goal make him a balanced midfielder capable of contributing at both ends of the pitch. Seen as a long-term project, Santos represents the next generation of Brazilian talent."));
        players.add(new Player(10, "Cole Palmer", R.drawable.thumb_cole_palmer, R.drawable.img_cole_palmer, "Attacking Midfielder",
                "Cole Palmer is an exceptionally creative English playmaker who excels in tight spaces and delivers decisive moments in attack. After joining Chelsea from Manchester City in 2023, Palmer quickly became one of the club’s standout performers, known for his dribbling, vision, and composed finishing. His intelligence between the lines and flair make him a versatile attacker capable of operating as a winger or central creator, bringing unpredictability to Chelsea’s forward play."));
        players.add(new Player(11, "Estêvão", R.drawable.thumb_estevao, R.drawable.img_estevao, "Attacking Midfielder",
                "Estêvão, nicknamed 'Messinho' in Brazil, is one of world football’s brightest teenage prospects. Joining Chelsea from Palmeiras in 2025, he is celebrated for his breathtaking dribbling, acceleration, and ability to take on defenders one-on-one. Despite his youth, Estêvão’s technical quality and confidence make him a player capable of changing games. Chelsea see him as a future superstar, with immense potential to develop into an elite attacking talent."));
        players.add(new Player(12, "Alejandro Garnacho", R.drawable.thumb_alejandro_garnacho, R.drawable.img_alejandro_garnacho, "Winger",
                "Alejandro Garnacho is an explosive Argentine winger renowned for his direct dribbling, pace, and fearless attacking style. After emerging at Manchester United, Garnacho made a high-profile move to Chelsea in 2025. His ability to create chances and score from wide areas brings a new dimension to Chelsea’s attack. Still only in his early twenties, Garnacho combines South American flair with Premier League grit, making him one of the most exciting young wingers in the league."));
        players.add(new Player(13, "Jamie Gittens", R.drawable.thumb_jamie_gittens, R.drawable.img_jamie_gittens, "Winger",
                "Jamie Gittens is a fast, skillful English winger who joined Chelsea from Borussia Dortmund in 2025. Known for his quick acceleration, tight control, and attacking instincts, Gittens poses a constant threat down the flanks. His experience in the Bundesliga helped him refine his tactical awareness and end product, and he is expected to play a crucial role in Chelsea’s new generation of attacking talent."));
        players.add(new Player(14, "Liam Delap", R.drawable.thumb_liam_delap, R.drawable.img_liam_delap, "Striker",
                "Liam Delap is a physically imposing English striker recognized for his strength, pressing intensity, and eye for goal. After progressing through Manchester City’s academy and impressing on loan at various clubs, he joined Chelsea from Ipswich Town in 2025. Delap’s hold-up play and movement make him a reliable target man capable of leading the line effectively. His relentless energy and determination reflect Chelsea’s push for a more dynamic attacking style."));
    }

    /**
     * Returns the full list of players.
     *
     * @return an {@link ArrayList} containing all mock {@link Player} objects
     */
    public static ArrayList<Player> getPlayers() {
        return players;
    }

    /**
     * Retrieves a specific player by their unique ID.
     *
     * @param id the player's identifier
     * @return the {@link Player} with the matching ID, or {@code null} if not found
     */
    public static Player getPlayerById(int id) {
        for (Player player : players) {
            if (player.id == id) return player;
        }
        return null;
    }
}
