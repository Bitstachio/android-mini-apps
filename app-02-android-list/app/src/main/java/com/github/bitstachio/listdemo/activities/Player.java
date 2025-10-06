package com.github.bitstachio.listdemo.activities;

/**
 * Represents a player entity containing basic identifying and descriptive information.
 * <p>
 * Each {@code Player} includes an ID, name, position, description, and references
 * to both thumbnail and full-sized image resources. This model is primarily used
 * for populating RecyclerView lists and detailed player views.
 */
public class Player {

    // =========================
    // Fields
    // =========================

    /**
     * Unique identifier for the player.
     */
    int id;

    /**
     * Player's full name.
     */
    String name;

    /**
     * Player's primary playing position (e.g., "Goalkeeper", "Winger").
     */
    String position;

    /**
     * Brief biographical or descriptive text about the player.
     */
    String description;

    /**
     * Resource ID for the player's detailed image.
     */
    int imageResId;

    /**
     * Resource ID for the player's thumbnail image (used in list previews).
     */
    int thumbnailResId;

    // =========================
    // Constructors
    // =========================

    /**
     * Creates a new {@code Player} instance.
     *
     * @param id             unique player ID
     * @param name           player's name
     * @param thumbnailResId resource ID for the player's thumbnail image
     * @param imageResId     resource ID for the player's detailed image
     * @param position       player's position on the field
     * @param description    brief player description or bio
     */
    public Player(int id, String name, int thumbnailResId, int imageResId, String position, String description) {
        this.id = id;
        this.name = name;
        this.thumbnailResId = thumbnailResId;
        this.imageResId = imageResId;
        this.position = position;
        this.description = description;
    }

    // =========================
    // Getters
    // =========================

    /**
     * @return the player's unique ID
     */
    public int getId() {
        return id;
    }

    /**
     * @return the player's name
     */
    public String getName() {
        return name;
    }

    /**
     * @return the player's position
     */
    public String getPosition() {
        return position;
    }

    /**
     * @return the player's description text
     */
    public String getDescription() {
        return description;
    }

    /**
     * @return the resource ID of the player's detailed image
     */
    public int getImageResId() {
        return imageResId;
    }

    /**
     * @return the resource ID of the player's thumbnail image
     */
    public int getThumbnailResId() {
        return thumbnailResId;
    }
}
