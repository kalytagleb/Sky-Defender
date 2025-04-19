package input;

/**
 * Manages keyboard input states for the game.
 */
public class Key {
    private boolean key_right;
    private boolean key_left;
    private boolean key_space;
    private boolean key_j;
    private boolean key_k;
    private boolean key_l;

    /**
     * Checks if the right key is pressed.
     *
     * @return true if the right key is pressed.
     */
    public boolean isKey_right() {
        return key_right;
    }

    /**
     * Sets the state of the right key.
     *
     * @param key_right the new state
     */
    public void setKey_right(boolean key_right) {
        this.key_right = key_right;
    }

    /**
     * Checks if the left key is pressed.
     *
     * @return true if the left key is pressed.
     */
    public boolean isKey_left() {
        return key_left;
    }

    /**
     * Sets the state of the left key.
     *
     * @param key_left the new state
     */
    public void setKey_left(boolean key_left) {
        this.key_left = key_left;
    }

    /**
     * Checks if the space key is pressed.
     *
     * @return true if the space key is pressed.
     */
    public boolean isKey_space() {
        return key_space;
    }

    /**
     * Sets the state of the left key.
     *
     * @param key_space the new state
     */
    public void setKey_space(boolean key_space) {
        this.key_space = key_space;
    }

    /**
     * Checks if the J key is pressed.
     *
     * @return true if the J key is pressed.
     */
    public boolean isKey_j() {
        return key_j;
    }

    /**
     * Sets the state of the left key.
     *
     * @param key_j the new state
     */
    public void setKey_j(boolean key_j) {
        this.key_j = key_j;
    }

    /**
     * Checks if the K key is pressed.
     *
     * @return true if the K key is pressed.
     */
    public boolean isKey_k() {
        return key_k;
    }

    /**
     * Sets the state of the left key.
     *
     * @param key_k the new state
     */
    public void setKey_k(boolean key_k) {
        this.key_k = key_k;
    }

    /**
     * Checks if the L key is pressed.
     *
     * @return true if the L key is pressed.
     */
    public boolean isKey_l() {return key_l;}

    /**
     * Sets the state of the left key.
     *
     * @param key_l the new state
     */
    public void setKey_l(boolean key_l) {
        this.key_l = key_l;
    }
}