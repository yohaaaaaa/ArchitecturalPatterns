package gatewayDTOs;

/**
 * Metal Dto for the Metal Gateway
 */
public class MetalDTO {
    private long id;
    private long dissolvedBy;

    /**
     * Metal Constructor
     * @param dissolvedBy
     */
    public MetalDTO(long id, long dissolvedBy)
    {
        this.id = id;
        this.dissolvedBy = dissolvedBy;
    }

    /**
     * setter for DissolvedBy
     * @param dissolvedBy
     */
    public void setDissolvedBy(long dissolvedBy)
    {
        this.dissolvedBy = dissolvedBy;
    }

    public long getId() { return id; }
    /**
     * getter for DissolvedBy
     * @return
     */
    public long getDissolvedBy()
    {
        return dissolvedBy;
    }
}
