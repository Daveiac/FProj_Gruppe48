package uml;
/**
 * @startuml

JFrame <|-- NewAppointmentView
JFrame <|-- AppointmentOverview

NewAppointmentView *-- JLabel
NewAppointmentView *-- JTextField
NewAppointmentView *-- JTextArea
NewAppointmentView *-- JCheckBox
NewAppointmentView *-- JComboBox
NewAppointmentView *-- JList
NewAppointmentView *-- JButton
NewAppointmentView --|> JPanel

AppointmentOverview *-- JLabel
AppointmentOverview *-- JTextArea
AppointmentOverview *-- JComboBox
AppointmentOverview *-- JList
AppointmentOverview *-- JButton
AppointmentOverview --|> JPanel

Meeting <-- NewAppointmentView
Meeting <-- AppointmentOverview







@enduml
 * @author David
 *
 */

public class UML_CD_Frames {

}
