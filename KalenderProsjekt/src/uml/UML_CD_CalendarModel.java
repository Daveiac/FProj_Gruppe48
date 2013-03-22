package uml;
/**
 * @startuml

CalendarModel --> PropertyChangeSupport
CalendarModel *-- ArrayList
CalendarModel *-- Person : user
CalendarModel *-- GregorianCalendar

ArrayList *-- Meeting : meetings
ArrayList *-- MeetingRoom :rooms
ArrayList *-- Notification : notifications
ArrayList *-- Person : persons
ArrayList *-- Alarm : alarms

Meeting *-- Team

@enduml
 * @author David
 *
 */

public class UML_CD_CalendarModel {

}
