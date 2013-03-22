package uml;

/**
 * @startuml


JPanel <|-- DefaultView

DefaultView --> CalendarModel
DefaultView --> JFrame




interface CalendarView
DefaultView *-- CalendarView
DefaultView *-- NotiPanelView
DefaultView *-- SharedCalendarView
DefaultView *-- AppointmentView
class SharedCalendarView

DefaultView *-- JToggleButton : dayBtn
DefaultView *-- JToggleButton : weekBtn
DefaultView *-- JToggleButton : monthBtn
DefaultView *-- JToggleButton : calendar
DefaultView *-- JToggleButton : meeting
DefaultView *-- JButton : prevBtn
DefaultView *-- JButton : nextBtn
DefaultView *-- JButton : logOut


@enduml
 * @author David
 *
 */

public class UML_CD_DefaultView {

}
