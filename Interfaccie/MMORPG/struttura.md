
# Schema configurazione 

```text
com.tuonome.fantasy
│
├── Main.java
│
├── core
│   ├── GameEngine.java
│   ├── GameLoop.java
│   ├── GameState.java
│   └── EventBus.java
│
├── world
│   ├── WorldMap.java
│   ├── Zone.java
│   ├── ZoneType.java
│   │
│   ├── board
│   │   ├── Board.java
│   │   ├── Cell.java
│   │   └── Position.java
│   │
│   └── environment
│       ├── EnvironmentEffect.java
│       ├── SandStormEffect.java
│       ├── JunglePoisonEffect.java
│       └── HeatEffect.java
│
├── entity
│   ├── Character.java
│   ├── Stats.java
│   │
│   ├── player
│   │   └── Player.java
│   │
│   ├── enemy
│   │   ├── Enemy.java
│   │   ├── EnemyType.java
│   │   ├── EnemyFactory.java
│   │   │
│   │   └── ai
│   │       ├── AIController.java
│   │       ├── AggressiveAI.java
│   │       ├── DefensiveAI.java
│   │       └── RandomAI.java
│   │
│   ├── characterclass
│   │   ├── CharacterClass.java
│   │   ├── MageClass.java
│   │   ├── SwordsmanClass.java
│   │   └── AssassinClass.java
│   │
│   └── equipment
│       ├── EquipmentManager.java
│       └── EquipmentSlot.java
│
├── combat
│   ├── CombatSystem.java
│   ├── DamageCalculator.java
│   ├── CombatContext.java
│   │
│   ├── effect
│   │   ├── Effect.java
│   │   ├── BurnEffect.java
│   │   ├── PoisonEffect.java
│   │   └── SlowEffect.java
│   │
│   └── strategy
│       ├── AttackStrategy.java
│       ├── MeleeAttack.java
│       └── MagicAttack.java
│
├── movement
│   ├── MovementSystem.java
│   ├── PathFinder.java
│   │
│   └── type
│       ├── MovementType.java
│       ├── WalkMovement.java
│       ├── LightStepMovement.java
│       └── TeleportMovement.java
│
├── item
│   ├── Item.java
│   ├── ItemRarity.java
│   ├── ItemStack.java
│   ├── Inventory.java
│   │
│   ├── consumable
│   │   ├── Potion.java
│   │   └── effect
│   │       ├── HealEffect.java
│   │       └── ManaEffect.java
│   │
│   ├── equipment
│   │   ├── Equipment.java
│   │   ├── Weapon.java
│   │   └── Armor.java
│   │
│   └── loot
│       ├── LootTable.java
│       ├── LootEntry.java
│       └── DropSystem.java
│
└── turn
└── TurnManager.java
```