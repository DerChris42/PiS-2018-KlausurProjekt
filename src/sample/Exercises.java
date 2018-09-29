package sample;

import java.util.HashMap;
import java.util.Map;

class Exercises {

    private int exerciseCounter = 0;
    public Exercises(){
        fillMaps();
    }
    private Map<Integer,String> exerciseMap = new HashMap<>();

    // Klasse 7 Thema 5 - Gleichungen und Terme Quelle: www.strobl-f.de/m7.html Aufgaben sind zum Teil leicht abgeändert!
    private final Map<Integer,String> gleichungenUndTermeSubtopic0 = new HashMap<>();
    private final Map<Integer,String> gleichungenUndTermeSubtopic1 = new HashMap<>();
    private final Map<Integer,String> gleichungenUndTermeSubtopic2= new HashMap<>();

    public String getTermeUndGleichungenExercise(boolean[] exerciseSelected){
        if(exerciseSelected[0]){
            exerciseMap.putAll(gleichungenUndTermeSubtopic0);
        }
        if(exerciseSelected[1]){
            exerciseMap.putAll(gleichungenUndTermeSubtopic1);
        }
        if(exerciseSelected[2]){
            exerciseMap.putAll(gleichungenUndTermeSubtopic2);
        }
        if(exerciseCounter==exerciseMap.size()){
            exerciseCounter=0;
            exerciseMap.clear();
            return "done";
        }
        else{
            String givenExercise =  exerciseMap.get(exerciseCounter);
            exerciseCounter++;
            return givenExercise;
        }
    }
    private void fillMaps(){
        gleichungenUndTermeSubtopic0.put(0,"Vereinfache den Term!%a−x+x−a+x−a+2x%-a+3x%a-a-a = -a \n -x+x+x+2x = 3x\n Alle a und alle x werden zusammengefasst.\n Zusammen ergibt dies den Term −a+3x");
        gleichungenUndTermeSubtopic0.put(1,"Vereinfache den Term!%−14a−(7−2a)%-12a-7%−14a−(7−2a)\nDas Minus vor der Klammer kehrt alle Vorzeichen um.\n-14a-7+2a Die a kann man miteinander verrechnen.\n -12a-7");
        gleichungenUndTermeSubtopic0.put(2,"Vereinfache den Term!%2xy−y+a+2y%a+2xy+y%2xy−y+a+2y\n Man kann nur 2y-y zusammenfassen");
        gleichungenUndTermeSubtopic0.put(3,"Vereinfache den Term!%−14a−(-7+2a)%-16a+7%Das Minus vor der Klammer kehrt alle Vorzeichen um.\n -14a+7-2a -> -14a-2a = -16a ");
        gleichungenUndTermeSubtopic0.put(4,"Klammere aus!%abc−acd%ac(b-d)%Sowohl im Minuend als auch im Subtrahend ist der\n Faktor ac enthalten. Diesen können wir \n dann ausklammern.");
        gleichungenUndTermeSubtopic1.put(5,"Löse die Gleichung!\nLösung im Format x=...%-7x+9 = -5%x=2% -7x+9=-5 | -9 \n -7x = -14 | :(-14) \n x=2");
        gleichungenUndTermeSubtopic1.put(6,"Löse die Gleichung!\nLösung im Format x=...%x+4 = 9x-(5-x)%x=1% x+4 = 9x-(5-x) -> Klammer auflösen!\n x+4 = 9x−5+x | -x \n 4 = 9x-5 | +5\n 9 = 9x | :9 \n x=1 ");
        gleichungenUndTermeSubtopic1.put(7,"Löse die Gleichung!\nLösung im Format x=...%(1/24)x = 0%x=0%(1/24)x = 0 | *24\n x=0");
        gleichungenUndTermeSubtopic1.put(8,"Löse die Gleichung!\nLösung im Format x=...%19+2x+3 = 44%x=11%19+2x+3 = 44 -> zusammenfassen\n 22+2x = 44 | -22\n 2x = 22 | :2\n x=11");
        gleichungenUndTermeSubtopic1.put(9,"Löse die Gleichung!\nLösung im Format x=...%8*2+10x = 8x-2%x=-9%8*2+10x = 8x-2 -> 8*2 =16\n 16+10x = 8x-2 | +2 \n 18+10x = 8x | -10x\n 18 = -2x | :(-2)\n x=-9");
        gleichungenUndTermeSubtopic2.put(10,"Löse die Textaufgabe!\nLösung im Format x=...%Eine Erbschaft von 140 000 Euro wird so unter drei Erben A, B, C aufgeteilt, dass A\n" + "20 000 Euro mehr erhalt als B und C zusammen, und die Erbschaft von B und C sich \n" + "wie 2:1 verhalt. Wie viel erhält C? %x=20000%\n" + "1. Sei x der Betrag (in Euro), den C erhält.\n" + "B erhält dann 2x,\n A erhält x+2x+20000 = 3x+20000.\n" + "x+2x+3x+20000 = 140000\n" + "6x = 120000\n x = 20000.\n" + "C erhält 20 000 Euro");
        gleichungenUndTermeSubtopic2.put(11,"Löse die Textaufgabe!\nLösung im Format x=...%Prufe durch Einsetzen, ob x = 1, 2, 3, 4, 5 eine für 90:x = x+27 Losung ist! %x=3%3. Mit x = 1 stünde da: 90 : 1 = 1 + 27,\n" + "also 90 = 28, also ist x = 1 keine Losung.\n" + "Mit x = 2: 45 = 29, also keine Losung.\n" + "Mit x = 3: 30 = 30, also Losung. \n" + "Mit x = 4: 22,5 = 31, also keine Losung.\n" + "Mit x = 5: 18 = 32, also keine Losung");
        gleichungenUndTermeSubtopic2.put(12,"Löse die Textaufgabe!\nLösung im Format x=...%Hans ist gerade 48 Jahre und sein Sohn Hänschen ist gerade 15 Jahre alt.\n" + "Nach wie vielen Jahren ist Hans doppelt so alt wie Hänschen dann ist?%x=18%48+x=2⋅(15+x)\n48+x=2⋅(15+x)\n48+x=30+2⋅x|−x−30\nx=18");
        gleichungenUndTermeSubtopic2.put(13,"Löse die Textaufgabe!\nLösung im Format x=...%In einem Verein mit 25 Mitgliedern haben 12 Mitglieder jeweils 2000€ eingezahlt. 12 weitere Mitglieder haben jeweils 1500€ beigesteuert. Auf den Vereinskonto befinden sich 17000€. Wie ist das zu erklären? Führe eine Rechnung mit einem x-Ansatz durch!%x=-25000%12⋅2000+12⋅1500+x=17000 -> Ausmultiplizieren\n 24000+18000+x=17000\n 42000+x=17000 | −42000\n x=−25000\n ⇒ Vor den Einzahlungen hatte der Verein 25000€ Schulden.");
        gleichungenUndTermeSubtopic2.put(14,"Löse die Textaufgabe!\nLösung im Format x=...%Das Zehnfache einer Zahl vermindert um 10 ist gleich dem sechsfachen der Zahl vermehrt um 2.\n" + "Wie heißt die Zahl?%x=3%10x-10 = 6x+2 | -6x\n 4x-10 = 2 | +10\n 4x = 12 | :4\n x=3");
    }
}