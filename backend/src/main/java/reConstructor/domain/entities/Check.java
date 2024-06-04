package reConstructor.domain.entities;

import jakarta.persistence.*;
import jakarta.persistence.Table;
import reConstructor.domain.interfaces.ICheck;

import java.sql.Timestamp;
import java.util.*;

@Entity
@Table(name = "checks")
public class Check implements ICheck {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;
    @Column(name = "order_id")
    private UUID orderId;
    @Column(name = "report_url")
    private String reportUrl;
    @ManyToOne
    @JoinColumn(name = "table_id", nullable = false)
    private Tables restTable;
    @Column(name = "comment")
    private String comment;
    @Column(name = "created_at")
    private Timestamp createdAt;
    @OneToMany(mappedBy = "check", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CheckDetails> checkDetails;

    public Check() {
    }

    public Check(long id, UUID orderId, String reportUrl, Tables restTable, String comment, Timestamp createdAt,
                 List<CheckDetails> checkDetails) {
        this.id = id;
        this.orderId = orderId;
        this.reportUrl = reportUrl;
        this.restTable = restTable;
        this.comment = comment;
        this.createdAt = createdAt;
        this.checkDetails = checkDetails;
    }

    @Override
    public long getId() {
        return id;
    }

    @Override
    public void setId(long id) {
        this.id = id;
    }

    @Override
    public Tables getRestTable() {
        return restTable;
    }

    @Override
    public void setRestTable(Tables restTable) {
        this.restTable = restTable;
    }

    @Override
    public UUID getOrderId() {
        return orderId;
    }

    @Override
    public void setOrderId(UUID orderId) {
        this.orderId = orderId;
    }

    @Override
    public String getReportUrl() {
        return reportUrl;
    }

    @Override
    public void setReportUrl(String reportUrl) {
        this.reportUrl = reportUrl;
    }

    @Override
    public String getComment() {
        return comment;
    }

    @Override
    public void setComment(String comment) {
        this.comment = comment;
    }

    @Override
    public Timestamp getCreatedAt() {
        return createdAt;
    }

    @Override
    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public List<CheckDetails> getCheckDetails() {
        return checkDetails;
    }

    @Override
    public void setCheckDetails(List<CheckDetails> checkDetails) {
        this.checkDetails = checkDetails;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Check check = (Check) o;
        return id == check.id && Objects.equals(orderId, check.orderId) && Objects.equals(reportUrl, check.reportUrl)
                && Objects.equals(restTable, check.restTable) && Objects.equals(comment, check.comment)
                && Objects.equals(createdAt, check.createdAt) && Objects.equals(checkDetails, check.checkDetails);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, orderId, reportUrl, restTable, comment, createdAt, checkDetails);
    }

    @Override
    public String toString() {
        return "Check{" +
                "id=" + id +
                ", orderId=" + orderId +
                ", reportUrl='" + reportUrl + '\'' +
                ", restTable=" + restTable +
                ", comment='" + comment + '\'' +
                ", createdAt=" + createdAt +
                ", checkDetails=" + checkDetails +
                '}';
    }
}
