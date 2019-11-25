package jbc.oct21.jindanupajit.jobposting.util;

import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public abstract class Casting {

    public static class Integer {
        public static int from(String s) {
            try {
                return java.lang.Integer.parseInt(s);
            } catch (NumberFormatException e) {
                return 0;
            }
        }
    }

    public static class Long {
        public static long from(String s) {
            try {
                return java.lang.Long.parseLong(s);
            } catch (NumberFormatException e) {
                return 0;
            }
        }
    }

    public static class Double {
        public static double from(String s) {
            try {
                return java.lang.Double.parseDouble(s);
            } catch (NumberFormatException e) {
                return 0;
            }
        }
    }

    public static class UTC {
        public static java.time.OffsetDateTime now(){
            return java.time.OffsetDateTime.now(ZoneOffset.UTC);
        }

        public static java.time.OffsetDateTime from(String s){
            return java.time.OffsetDateTime.parse(s).atZoneSameInstant(ZoneOffset.UTC).toOffsetDateTime();
        }

        public static java.time.OffsetDateTime from(ZoneId zoneId, String d, String t){
            return from(zoneId, String.format("%sT%s:00+00:00",d,t));

        }
        public static java.time.OffsetDateTime from(ZoneId zoneId, String s){
            return from(zoneId, java.time.OffsetDateTime.parse(
                    String.format("%s:00+00:00",s)
                    , DateTimeFormatter.ISO_OFFSET_DATE_TIME)
                    .atZoneSimilarLocal(zoneId).toOffsetDateTime());

        }
        public static java.time.OffsetDateTime from(ZoneId zoneId, java.time.OffsetDateTime offsetDateTime){
            return from(offsetDateTime.atZoneSimilarLocal(zoneId).toOffsetDateTime());

        }
        public static java.time.OffsetDateTime from(java.time.OffsetDateTime offsetDateTime){
            return offsetDateTime.atZoneSameInstant(ZoneOffset.UTC).toOffsetDateTime();
        }
        public static java.time.OffsetDateTime from(java.sql.Timestamp timestamp) {
            return ZonedDateTime.of(timestamp.toLocalDateTime(), ZoneId.systemDefault()).toOffsetDateTime();
        }
        public static java.time.OffsetDateTime to(ZoneId zoneId, java.time.OffsetDateTime offsetDateTime){
            return offsetDateTime.atZoneSameInstant(zoneId).toOffsetDateTime();
        }
    }


    public static class Timestamp {
        public static java.sql.Timestamp to(ZoneId zoneId, java.sql.Timestamp timestamp) {
            return java.sql.Timestamp.valueOf(timestamp.toLocalDateTime().atZone(ZoneId.systemDefault()).toOffsetDateTime()
                    .atZoneSameInstant(zoneId).toLocalDateTime());
        }

        public static java.sql.Timestamp from(OffsetDateTime offsetDateTime) {
            return java.sql.Timestamp.valueOf(offsetDateTime.atZoneSameInstant(ZoneId.systemDefault()).toLocalDateTime());
        }
        public static java.sql.Timestamp from(ZoneId zoneId, java.sql.Timestamp timestamp) {
            return from(ZonedDateTime.of(timestamp.toLocalDateTime(), zoneId).toOffsetDateTime());
        }


        public static java.sql.Timestamp from(String s) {
            return java.sql.Timestamp.valueOf(s);
        }

        public static java.sql.Timestamp from(String d, String t) {
            String timestampString = String.format("%s %s:00.000000000", d,t);
            // System.out.println(timestampString);
            return java.sql.Timestamp.valueOf(timestampString);
        }

        public static java.sql.Timestamp from(int yyyy, int MM, int dd) {
            return java.sql.Timestamp.valueOf(String.format("%04d-%02d-%02d 12:00:00.000000", yyyy, MM, dd));
        }

        public static java.sql.Timestamp from(int yyyy, int MM, int dd, int hh, int mm) {
            return java.sql.Timestamp.valueOf(String.format("%04d-%02d-%02d %02d:%02d:00.000000", yyyy, MM, dd, hh, mm));
        }

        public static java.sql.Timestamp now() {
            return new java.sql.Timestamp(System.currentTimeMillis());
        }

        public static java.sql.Timestamp nowNoSec() {
            java.sql.Timestamp ts = new java.sql.Timestamp(System.currentTimeMillis());
            return from(ts.toLocalDateTime().format(DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:00.000")));
        }
    }

    public static class MinuteDuration {
        public static int from(int hh, int mm) {
            return hh*60+mm;
        }
    }

}
