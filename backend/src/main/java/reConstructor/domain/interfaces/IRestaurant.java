package reConstructor.domain.interfaces;

import java.util.Set;

import reConstructor.domain.entities.Moderator;
import reConstructor.domain.entities.Staff;
import reConstructor.domain.entities.Tables;

public interface IRestaurant {

    long getId();

    void setId(long id);

    String getCode();

    void setCode(String code);

    String getPhoneNumber();

    void setPhoneNumber(String phoneNumber);

    String getAddress();

    void setAddress(String address);

    boolean isActive();

    void setActive(boolean active);

    String getWebsite();

    void setWebsite(String website);

    String getLogoUrl();

    void setLogoUrl(String logoUrl);

    Set<Tables> getTables();

    Set<Staff> getStaffList();

    void setStaffList(Set<Staff> staffList);

    void setTables(Set<Tables> tables);

    Moderator getModerator();

    void setModerator(Moderator moderator);
}
