package uml;
/**
 * 
 * @startuml

JFrame <|-- DefaultView
JFrame <|-- Login
JFrame <|-- NewAppointmentView
JFrame <|-- AppointmentOverview

Meeting --* NewAppointmentView
Meeting --* AppointmentOverview

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
PropertyChangeListener <|-- NotiPanelView
PropertyChangeListener <|-- SharedCalendarView
PropertyChangeListener <|-- AppointmentView


DefaultView *-- CalendarView
DefaultView *-- NotiPanelView
DefaultView *-- SharedCalendarView
DefaultView *-- AppointmentView
Program *-- JFrame

CalendarModel --> PropertyChangeSupport
CalendarModel *-- ArrayList


CalendarModel <-- DefaultView
CalendarModel <-- DayView
CalendarModel <-- WeekView
CalendarModel <-- MonthView
CalendarModel <-- NotiPanelView
CalendarModel <-- SharedCalendarView
CalendarModel <-- AppointmentView
CalendarModel <-- NewAppointmentView
CalendarModel <-- AppointmentOverview


ArrayList *-- Meeting
ArrayList *-- MeetingRoom
ArrayList *-- Notification
ArrayList *-- Person
ArrayList *-- Alarm

Meeting *-- Team

@enduml
 * @author David
 *
 */
