package reConstructor.domain.dto;

import java.util.List;

public class CheckDto {

    private long id;
    private long restaurantId;
    private String tableNumber;
    private String comment;
    private List<CheckDetailsDto> checkDetails;

    public CheckDto() {
    }

    public CheckDto(long id, long restaurantId, String tableNumber, String comment,
                    List<CheckDetailsDto> checkDetails) {
        this.id = id;
        this.restaurantId = restaurantId;
        this.tableNumber = tableNumber;
        this.comment = comment;
        this.checkDetails = checkDetails;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTableNumber() {
        return tableNumber;
    }

    public void setTableNumber(String tableNumber) {
        this.tableNumber = tableNumber;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public List<CheckDetailsDto> getCheckDetails() {
        return checkDetails;
    }

    public void setCheckDetails(List<CheckDetailsDto> checkDetails) {
        this.checkDetails = checkDetails;
    }

    public long getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(String codeRestaurant) {
        this.restaurantId = restaurantId;
    }
}
