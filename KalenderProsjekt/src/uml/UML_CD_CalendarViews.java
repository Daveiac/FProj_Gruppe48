package uml;
/**
 * @startuml

interface CalendarView
CalendarView <|-- DayView
CalendarView <|-- WeekView
CalendarView <|-- MonthView

JTable *-- DayView
JTable *-- WeekView
JTable *-- MonthView

interface PropertyChangeListener
PropertyChangeListener <|-- DayView
PropertyChangeListener <|-- WeekView
PropertyChangeListener <|-- MonthView

CalendarModel <-- DayView
CalendarModel <-- WeekView
CalendarModel <-- MonthView

JScrollPane *-- MonthView
JScrollPane *-- WeekView
JScrollPane *-- DayView

GregorianCalendar *-- MonthView
GregorianCalendar *-- WeekView
GregorianCalendar *-- DayView

TableModel <-- JTable

@enduml
 * @author David
 *
 */

public class UML_CD_CalendarViews {

}
