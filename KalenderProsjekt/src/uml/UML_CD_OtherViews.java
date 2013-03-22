package uml;
/**
 * @startuml
JScrollPane --* AppointmentView

JPanel --* AppointmentView
JPanel --* NotiPanelView
JPanel --* SharedCalendarView
JList --* NotiPanelView

JCheckBox --* SharedCalendarView
JLabel --* SharedCalendarView
JLabel --* AppointmentView

CalendarModel <-- AppointmentView
CalendarModel <-- NotiPanelView
CalendarModel <-- SharedCalendarView
@enduml
 * @author David
 *
 */

public class UML_CD_OtherViews {

}
