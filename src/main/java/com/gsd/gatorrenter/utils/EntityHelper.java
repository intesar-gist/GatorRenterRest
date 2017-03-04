package com.gsd.gatorrenter.utils;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import javax.persistence.Query;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;

/**
 * Created by Intesar on 9/26/2016.
 */
@Component
public class EntityHelper {
    private static final Logger LOGGER = Logger.getLogger(EntityHelper.class.getName());

    private static final String ERR_INVALID_ENTITY_ID = "invalid entity id: %d";
    public static final String NOT_AVAILABLE = "NA";
    public static final String EMPTY_STRING = "";

    public static int getCountInBusinessEntity(Query countQuery) {
        Long o = (Long) countQuery.getSingleResult();
        if (o > Integer.MAX_VALUE) {
            throw new ArithmeticException("Value cannot fit in an int: " + o);
        }
        return o.intValue();
    }

    public static <T> List<T> makeNotNull(List<T> list) {
        return list != null ? list : new ArrayList<T>();
    }

    public static void validateEntityId(int entityId) {
        if (entityId <= 0) {
            throw new IllegalArgumentException(String.format(ERR_INVALID_ENTITY_ID, entityId));
        }
    }

    public static boolean notNull(Object o) {
        return o != null;
    }

    public static boolean allNotNull(Object... o) {
        if (o == null) {
            return false;
        }
        for (Object o1 : o) {
            if (o1 == null) {
                return false;
            }
        }

        return true;
    }

    public static String removeTrailingCommas(String str) {
        if(!isStringSet(str)) return str;

        return str.replaceAll(", $", "");
    }

    public static void validateEntityId(String entityId) {
        if ("".equals(entityId)) {
            throw new IllegalArgumentException(String.format(ERR_INVALID_ENTITY_ID, entityId));
        }
    }

    public static boolean notNullAndMoreZero(Integer value) {
        return (value != null) && (value > 0);
    }


    public static boolean isStringSet(String str) {
        if ((str != null) && !str.isEmpty()){
            return true;
        }
        return false;
    }

    public static String setDefaultStringIfEmptyOrNull(String str) {
        if ((str != null) && !str.isEmpty()){
            return str;
        }
        return "N/A";
    }

    public static Integer setDefaultIntegerIfEmptyOrNull(Integer val) {
        if (isIdSet(val)){
            return val;
        }
        return -1;
    }


    public static boolean isIdSet(Integer... ids) {
        if (ids == null){
            return false;
        }
        for (Integer id : ids) {
            if ( !((id != null) && (id > 0)) )
                return false;
        }

        return true;
    }


    public static boolean areObjectSet(Object... obj) {
        if (obj == null){
            return false;
        }
        for (Object object : obj) {
            if ( !(object != null) )
                return false;
        }

        return true;
    }

    @Deprecated //see above method
    public static boolean idIsSet(Integer... ids) {
        if (ids == null){
            return false;
        }
        for (Integer id : ids) {
            if ( !((id != null) && (id > 0)) )
                return false;
        }

        return true;
    }

    public static boolean idIsNotSet(Integer... ids) {
        if (ids == null) {
            return false;
        }
        for (Integer id : ids) {
            if (isIdSet(id)) {
                return false;
            }
        }
        return true;
    }

    public static String getIdOrHyphen(Integer id) {
        return isIdSet(id) ? id.toString() : "-";
    }

    public static boolean isIdSet(String id) {
        return isStringSet (id);
    }

    public static boolean isSet(Boolean b) {
        return b != null && b.equals(Boolean.TRUE);
    }

    public static boolean isSet(String s) {
        return isIdSet(s);
    }

    public static boolean isSet(Integer i) {
        return isIdSet(i);
    }

    public static boolean isNull(Object o) {
        return o == null;
    }

    public static boolean isNotNull(Object o) {
        return !isNull(o);
    }

    public static boolean isNotNull(Object... o) {
        for (Object o1 : o) {
            if (o1 == null){
                return false;
            }
        }
        return true;
    }

    public static boolean anyNull(Object... os) {
        for (Object o : os) {
            if (o == null) {
                return true;
            }
        }
        return false;
    }

    public static boolean allNull(Object... os) {
        for (Object o : os) {
            if (o != null) {
                return false;
            }
        }
        return true;
    }

    public static boolean anyEmpty(String... os) {
        for (String o : os) {
            if (!isSet(o)) {
                return true;
            }
        }
        return false;
    }

    public static boolean allEmpty(String... os) {
        for (String o : os) {
            if (isSet(o)) {
                return false;
            }
        }
        return true;
    }

    public static <T> Set<T> toSet(List<T> list) {
        return list != null ? new LinkedHashSet<T>(list) : new LinkedHashSet<T>();
    }

    public static <T> List<T> toList(Collection<T> set) {
        return set != null ? new ArrayList<T>(set) : new ArrayList<T>();
    }

    public static <T> List<T> safeList(List<T> list) {
        return list == null ? Arrays.<T>asList() : list;
    }
    public static <T> Collection<T> safeCollection(Collection<T> list) {
        return list == null ? Collections.<T>emptyList() : list;
    }

    public static <T> Integer indexOf(Set<T> set, T o) {
        Integer i = 0;
        for (T t : set) {
            if (t.equals(o)) {
                return i;
            }
            i++;
        }
        return 0;
    }
    public static <T> void setOnHashSet(Set<T> set, T o, Integer position) {
        List<T> ts = new ArrayList<T>(set);
        ts.set(position, o);
        set = new HashSet<T>(ts);
    }

    public static boolean isSingleElementCollection(List<String> tails) {
        if (tails == null) {
            return false;
        }
        return tails.size() == 1;
    }

    public static Boolean isListPopulated(List<?> list) {
        return list != null && !list.isEmpty();
    }

    public static Boolean isSetPopulated(Set<?> set) {
        return set != null && !set.isEmpty();
    }

    public static Boolean isListPopulatedNotNull(List<?> list) {
        list.removeAll(Collections.singleton(null));
        return list != null && !list.isEmpty();
    }


    /*
    * Remove duplicate objects from the supplied list maintaining the order of the elements
    */
    public static <T> List<T> removeDuplicates(List<T> list){
        if(!CollectionUtils.isEmpty(list)){
            Set<T> set = new LinkedHashSet<T>();
            set.addAll(list);
            list.clear();
            list.addAll(set);
        }
        return list;
    }

    public static Boolean isPageNumAndPageSizeSet(Integer pageNum, Integer pageSize) {
        return pageNum != null && pageNum >= 0 && pageSize != null && pageSize > 0;
    }

    /*
    * Returns the full name in the format: firstName + middleName + lastName
     */
    public static String getPersonName(String firstName, String middleName, String lastName) {
        StringBuilder name = new StringBuilder();
        name.append(firstName);
        if(isSet(middleName)) {
            name.append(" ").append(middleName);
        }
        if(isSet(lastName)) {
            name.append(" ").append(lastName);
        }
        return name.toString();
    }

    /*
   * Returns the rounded double to the specified number of decimal places
   */
    public static double round(double value, int places) {
        if (places < 0) throw new IllegalArgumentException();

        BigDecimal bd = new BigDecimal(value);
        bd = bd.setScale(places, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }

    public static String getDateOfMonthSuffix(final int day) {
        if (day < 1 || day > 31)
            throw new IllegalArgumentException(String.format("Illegal day of month %d.", day));

        if (day >= 11 && day <= 13) {
            return "th";
        }
        switch (day) {
            case 1:
                return "st";
            case 2:
                return "nd";
            case 3:
                return "rd";
            default:
                return "th";
        }
    }

    public static Double toDouble(Object obj) {
        if (obj != null) {
            if (obj instanceof BigDecimal) {
                return BigDecimal.class.cast(obj).doubleValue();
            }
        }
        return null;
    }
    public static Float toFloat(Object obj) {
        if (obj != null) {
            if (obj instanceof BigDecimal) {
                return BigDecimal.class.cast(obj).floatValue();
            }
        }
        return null;
    }
    public static Long toLong(Object obj) {
        if (obj != null) {
            if (obj instanceof BigDecimal) {
                return BigDecimal.class.cast(obj).longValue();
            }
        }
        return null;
    }

    public static Integer toInteger(Object obj) {
        if (obj != null) {
            if (obj instanceof BigDecimal) {
                return BigDecimal.class.cast(obj).intValue();
            }
        }
        return null;
    }

    public static String toString(Object obj) {
        if (obj != null) {
            if (obj instanceof String) {
                return String.class.cast(obj);
            }
        }
        return null;
    }
    public static Date toDate(Object obj) {
        if (obj != null) {
            if (obj instanceof Date) {
                return Date.class.cast(obj);
            }
        }
        return null;
    }
    public static boolean isNotBlankOrNa(String str) {
        return StringUtils.isNotBlank(str) && !NOT_AVAILABLE.equalsIgnoreCase(str);
    }


}
