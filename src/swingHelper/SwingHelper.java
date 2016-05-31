package swingHelper;

import java.util.Calendar;
import java.util.Date;

import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerDateModel;
import javax.swing.SpinnerModel;

public class SwingHelper {

    /**
     * Adds to the given <code>JComponent</code> two date spinners  labeled "To" and "From". Used to specify a specific date range. The  default range is -10 years from today's date through today's date.
     * @param c - <code>JComponent</code> to add the date spinners to
     */
    public static void addDateRangePanel(JComponent c){
        Calendar calendar=Calendar.getInstance();
        JSpinner dateSpinner;
        Date initDate=calendar.getTime();
        Date latestDate=calendar.getTime();
        calendar.add(Calendar.YEAR,-10);
        Date earliestDate=calendar.getTime();
        SpinnerModel fromModel=new SpinnerDateModel(initDate,earliestDate,latestDate,Calendar.DAY_OF_MONTH);
        dateSpinner=addLabeledSpinner("From: ",fromModel,false);
        dateSpinner.setEditor(new JSpinner.DateEditor(dateSpinner,"dd MMMM yyyy"));
        SpinnerModel toModel=new SpinnerDateModel(initDate,earliestDate,latestDate,Calendar.DAY_OF_MONTH);
        dateSpinner=addLabeledSpinner("To: ",toModel,true);
        dateSpinner.setEditor(new JSpinner.DateEditor(dateSpinner,"dd MMMM yyyy"));
    }

    /**
     * Creates a <code>JSpinner</code> used for selecting the a date to the given <code>JComponent</code>. The default range is -100 years from today's date through today's date.
     * @return a JPanel containing one date spinner used for specifying a date
     */
    public static JPanel createDateSpinnerPanel(){
        JPanel datePanel=new JPanel();
        Calendar calendar=Calendar.getInstance();
        JSpinner dateSpinner;
        calendar.add(Calendar.YEAR,-100);
        Date initDate=calendar.getTime();
        Date latestDate=calendar.getTime();
        calendar.add(Calendar.YEAR,-100);
        Date earliestDate=calendar.getTime();
        SpinnerModel toModel=new SpinnerDateModel(initDate,earliestDate,latestDate,Calendar.DAY_OF_MONTH);
        dateSpinner=SwingHelper.addLabeledSpinner("",toModel, true);
        dateSpinner.setEditor(new JSpinner.DateEditor(dateSpinner,"dd MMMM yyyy"));
        return datePanel;
    }

    /**
     * Adds a <code>JSpinner</code> used for selecting the a date to the given <code>JComponent</code>. The default range is -100 years from today's  date through today's date.
     * @param  - the <code>JCompoent</code> to add the date spinner to
     * @param label - the text label to attach to the date spinner; set to ""to add an unlabeled date spinner
     * @return the <code>JSpinner</code>date spinner added to the component
     */
    public static JSpinner addDateSpinner(String label){
        Calendar calendar=Calendar.getInstance();
        JSpinner dateSpinner;
        calendar.add(Calendar.YEAR,0);
        Date initDate=calendar.getTime();
        Date latestDate=calendar.getTime();
        calendar.add(Calendar.YEAR,-100);
        Date earliestDate=calendar.getTime();
        SpinnerModel toModel=new SpinnerDateModel(initDate,earliestDate,latestDate,Calendar.DAY_OF_MONTH);
        dateSpinner=SwingHelper.addLabeledSpinner(label,toModel,true);
        dateSpinner.setEditor(new JSpinner.DateEditor(dateSpinner,"dd MMMM yyyy"));
        return dateSpinner;
    }

    private static JSpinner addLabeledSpinner(
            String label,
            SpinnerModel model, boolean b) {
        JLabel l = new JLabel(label);
        //c.add(l);

        JSpinner spinner = new JSpinner(model);
        l.setLabelFor(spinner);
        //c.add(spinner);

        return spinner;
    }
}
