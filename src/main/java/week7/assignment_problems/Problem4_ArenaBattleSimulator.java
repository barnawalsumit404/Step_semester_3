public class Problem4_ArenaBattleSimulator {
    interface Attackable {
        String attack();
        String attack(String weaponName);
    }

    interface Defendable {
        String defend();
    }

    abstract static class GameCharacter {
        private static int counter = 1000;
        private final String characterId;

        GameCharacter() {
            counter++;
            this.characterId = "CHAR-" + counter;
        }

        public abstract String getSpecialMove();

        public String getCharacterId() {
            return characterId;
        }
    }

    static class Warrior extends GameCharacter implements Attackable, Defendable {
        private final String name;

        public Warrior(String name) {
            this.name = name;
        }

        @Override
        public String attack() {
            return name + " strikes with a blade";
        }

        @Override
        public String attack(String weaponName) {
            return name + " strikes with an " + weaponName;
        }

        @Override
        public String defend() {
            return name + " raises a shield";
        }

        @Override
        public String getSpecialMove() {
            return name + " unleashes Whirlwind Slash";
        }
    }

    static class Trap implements Defendable {
        private final String trapType;

        public Trap(String trapType) {
            this.trapType = trapType;
        }

        @Override
        public String defend() {
            return trapType + " triggers automatically";
        }
    }

    static void resolveDefense(Defendable[] combatants) {
        for (Defendable fighter : combatants) {
            if (fighter != null) {
                System.out.println(fighter.defend());
            }
        }
    }

    public static void main(String[] args) {
        Warrior w = new Warrior("Kael");
        System.out.println(w.attack());
        System.out.println(w.attack("Iron Sword"));
        System.out.println(w.defend());
        System.out.println(w.getSpecialMove());

        Trap t = new Trap("Spike Pit");
        System.out.println(t.defend());

        resolveDefense(new Defendable[]{w, t});
    }
}
