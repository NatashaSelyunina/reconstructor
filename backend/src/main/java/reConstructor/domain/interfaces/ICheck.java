package reConstructor.domain.interfaces;

import reConstructor.domain.entities.CheckDetails;
import reConstructor.domain.entities.Tables;

import java.sql.Timestamp;
import java.util.List;
import java.util.UUID;

public interface ICheck {

    long getId();

    void setId(long id);

    Tables getRestTable();

    void setRestTable(Tables restTable);

    UUID getOrderId();

    void setOrderId(UUID orderId);

    String getReportUrl();

    void setReportUrl(String reportUrl);

    String getComment();

    void setComment(String comment);

    Timestamp getCreatedAt();

    void setCreatedAt(Timestamp createdAt);

    List<CheckDetails> getCheckDetails();

    void setCheckDetails(List<CheckDetails> checkDetails);
}
