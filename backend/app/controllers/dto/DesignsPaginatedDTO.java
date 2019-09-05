package controllers.dto;

import io.vavr.collection.List;

public class DesignsPaginatedDTO {

    private List<DesignDTO> designs;
    private int count;

    public DesignsPaginatedDTO(List<DesignDTO> designs, int count) {
        this.designs = designs;
        this.count = count;
    }

    public List<DesignDTO> getDesigns() {
        return designs;
    }

    public void setDesigns(List<DesignDTO> designs) {
        this.designs = designs;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
