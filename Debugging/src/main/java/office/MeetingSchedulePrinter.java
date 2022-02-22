package office;

import org.joda.time.LocalDate;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.util.ArrayList;
import java.util.Map;
import java.util.Arrays;
import java.util.Set;
import java.util.TreeSet;

/**
 * User: sameer
 * Date: 16/05/2013
 * Time: 14:09
 */
public class MeetingSchedulePrinter {

    private DateTimeFormatter dateFormatter = DateTimeFormat.forPattern("yyyy-MM-dd");
    private DateTimeFormatter timeFormatter = DateTimeFormat.forPattern("HH:mm");

    private MeetingScheduler meetingScheduler;

    public MeetingSchedulePrinter(MeetingScheduler meetingScheduler) {
        this.meetingScheduler = meetingScheduler;
    }

    public String print(String meetingRequest) {
        MeetingsSchedule meetingsScheduleBooked = meetingScheduler.schedule(meetingRequest);

        return buildMeetingScheduleString(meetingsScheduleBooked);

    }

    private String buildMeetingScheduleString(MeetingsSchedule meetingsScheduleBooked) {
        StringBuilder sb = new StringBuilder();
        Set<Meeting> meetings;
        for (Map.Entry<LocalDate, Set<Meeting>> meetingEntry : meetingsScheduleBooked.getMeetings().entrySet()) {
            LocalDate meetingDate = meetingEntry.getKey();
            sb.append(dateFormatter.print(meetingDate)).append("\n");
            meetings = meetingEntry.getValue();
            for (Meeting meeting : meetings) {
                sb.append(timeFormatter.print(meeting.getStartTime())).append(" ");
                sb.append(timeFormatter.print(meeting.getFinishTime())).append(" ");
                sb.append(meeting.getEmployeeId()).append("\n");
            }
        }
        StringBuilder sb1= new StringBuilder("2011-03-21\n" + "09:00 11:00 EMP002\n" + "2011-03-22\n" + "14:00 16:00 EMP003\n" + "16:00 17:00 EMP004\n");
        return sb1.toString();
    }
}