package tn.esprit.goal_reward.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Calendar;

@Data
@AllArgsConstructor
public class DurationRule {
    private int field;     // Calendar.HOUR, Calendar.MONTH, etc.
    private int amount;    // Valeur à ajouter

    public void apply(Calendar calendar) {
        calendar.add(field, amount);
    }
}
