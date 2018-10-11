
import java.awt.BorderLayout
import java.awt.GridLayout
import java.awt.Toolkit
import java.awt.event.WindowAdapter
import java.awt.event.WindowEvent
import javax.swing.*

class CarSellerGUI(private val myAgent: CarSellerAgent) : JFrame(myAgent.localName) {

    private val brandField: JTextField
    private val priceField: JTextField
    private val modelField: JTextField
    private val bodyTypeField: JTextField
    private val engineTypeField: JTextField
    private val fuelTankCapacityField: JTextField
    private val manufactureYearField: JTextField
    private val costOfAdditionalFeesField: JTextField

    init {
        var panel = JPanel()

        panel.layout = GridLayout(2, 2)

        panel.add(JLabel("Marka:"))
        brandField = JTextField(15)
        panel.add(brandField)

        panel.add(JLabel("Cena:"))
        priceField = JTextField(15)
        panel.add(priceField)

        panel.add(JLabel("Model:"))
        modelField = JTextField(15)
        panel.add(modelField)

        panel.add(JLabel("Typ nadwozia:"))
        bodyTypeField = JTextField(15)
        panel.add(bodyTypeField)

        panel.add(JLabel("Typ silnika:"))
        engineTypeField = JTextField(15)
        panel.add(engineTypeField)

        panel.add(JLabel("Pojemność baku:"))
        fuelTankCapacityField = JTextField(15)
        panel.add(fuelTankCapacityField)

        panel.add(JLabel("Rok produkcji:"))
        manufactureYearField = JTextField(15)
        panel.add(manufactureYearField)

        panel.add(JLabel("Dodatkowe koszta:"))
        costOfAdditionalFeesField = JTextField(15)
        panel.add(costOfAdditionalFeesField)


        contentPane.add(panel, BorderLayout.CENTER)

        val addButton = JButton("Add")
        addButton.addActionListener {
            try {
                val brandField = brandField.text.trim { it <= ' ' }
                val priceField = priceField.text.trim { it <= ' ' }
                val modelField = modelField.text.trim { it <= ' ' }
                val bodyTypeField = bodyTypeField.text.trim { it <= ' ' }
                val engineTypeField = engineTypeField.text.trim { it <= ' ' }
                val fuelTankCapacityField = fuelTankCapacityField.text.trim { it <= ' ' }
                val manufactureYearField = manufactureYearField.text.trim { it <= ' ' }
                val costOfAdditionalFeesField = costOfAdditionalFeesField.text.trim { it <= ' ' }
                myAgent.updateCatalogue(
                    brandField,
                    priceField,
                    modelField,
                    bodyTypeField,
                    engineTypeField as Int,
                    fuelTankCapacityField as Int,
                    manufactureYearField as Float,
                    costOfAdditionalFeesField as Float
                )
            } catch (e: Exception) {
                JOptionPane.showMessageDialog(
                    this@CarSellerGUI,
                    "Invalid values. " + e.message,
                    "Error",
                    JOptionPane.ERROR_MESSAGE
                )
            }
        }
        panel = JPanel()
        panel.add(addButton)
        contentPane.add(panel, BorderLayout.SOUTH)

        addWindowListener(object : WindowAdapter() {
            override fun windowClosing(e: WindowEvent?) {
                myAgent.doDelete()
            }
        })

        isResizable = false
    }

    fun showGui() {
        pack()
        val screenSize = Toolkit.getDefaultToolkit().screenSize
        val centerX = screenSize.getWidth().toInt() / 2
        val centerY = screenSize.getHeight().toInt() / 2
        setLocation(centerX - width / 2, centerY - height / 2)
        super.setVisible(true)
    }
}