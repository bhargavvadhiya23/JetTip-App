# JetTip 💰

A modern tip calculator Android application built with Jetpack Compose that helps you calculate tips and split bills among multiple people.

## Features ✨

- **Bill Input**: Enter your total bill amount
- **Tip Calculation**: Adjust tip percentage using an interactive slider (0-100%)
- **Bill Splitting**: Split the bill among 1-100 people
- **Real-time Updates**: All calculations update automatically as you adjust values
- **Clean UI**: Modern Material Design 3 interface with rounded corners and elegant styling

## Screenshots 📱
<img width="372" alt="Screenshot 2025-07-01 at 3 18 36 PM" src="https://github.com/user-attachments/assets/8e8e61b0-0dd9-4781-828d-a81c05f61975" />


## Architecture 🏗️

- **Language**: Kotlin
- **UI Framework**: Jetpack Compose
- **Architecture**: MVVM pattern with Compose state management

## Project Structure 📁

```
com.example.jettip/
├── MainActivity.kt          # Main activity and app entry point
├── components/
│   └── InputField.kt       # Custom input field component
├── widgets/
│   └── RoundIconBtn.kt     # Custom round icon button
├── ui/theme/
│   └── JetTipTheme.kt      # App theme configuration
└── Util/
    └── [Utility functions]  # Tip and bill calculation utilities
```

## Key Components 🧩

### MainActivity
- Main entry point of the application
- Sets up the Compose UI and theme

### TopHeader
- Displays the calculated amount per person
- Features a rounded card design with purple background

### BillForm
- Main form containing all interactive elements
- Handles bill input, tip slider, and split counter
- Manages state for all calculations

### Custom Components
- **InputField**: Custom text input with validation
- **RoundIconBtn**: Circular buttons for increment/decrement actions

## State Management 📊

The app uses Compose's built-in state management with:
- `mutableStateOf()` for bill input
- `mutableDoubleStateOf()` for decimal calculations
- `mutableIntStateOf()` for people counter
- `remember()` for state persistence across recompositions

## Calculations 🧮

The app performs three main calculations:
1. **Tip Amount**: `calculateToatlTip(billAmount, tipPercentage)`
2. **Total per Person**: `calculateTotalPerPerson(totalBill, splitBy, tipAmount)`
3. **Real-time Updates**: All values recalculate when inputs change

## Getting Started 🚀

### Prerequisites
- Android Studio Arctic Fox or later
- Kotlin 1.8+
- Android SDK 21+

### Installation
1. Clone the repository:
   ```bash
   git clone [your-repository-url]
   ```

2. Open the project in Android Studio

3. Sync the project with Gradle files

4. Run the app on an emulator or physical device

### Dependencies
Add these dependencies to your `build.gradle` file:

```kotlin
dependencies {
    implementation 'androidx.core:core-ktx:1.9.0'
    implementation 'androidx.lifecycle:lifecycle-runtime-ktx:2.6.2'
    implementation 'androidx.activity:activity-compose:1.8.1'
    implementation platform('androidx.compose:compose-bom:2023.10.01')
    implementation 'androidx.compose.ui:ui'
    implementation 'androidx.compose.ui:ui-graphics'
    implementation 'androidx.compose.ui:ui-tooling-preview'
    implementation 'androidx.compose.material3:material3'
    implementation 'androidx.compose.material:material-icons-extended'
}
```

## Usage Instructions 📝

1. **Enter Bill Amount**: Tap the input field and enter your total bill
2. **Adjust Tip**: Use the slider to set your desired tip percentage (0-100%)
3. **Split Bill**: Use the + and - buttons to set the number of people
4. **View Result**: The amount per person updates automatically at the top

## Code Highlights 💡

### Reactive UI Updates
```kotlin
val totalPerPersonState = remember {
    mutableDoubleStateOf(0.0)
}

// Automatic recalculation when values change
totalPerPersonState.doubleValue = calculateTotalPerPerson(
    totalBill = totalBillState.value.toDouble(),
    splitby = splitConter.intValue,
    tipAmount = tipAmountState.doubleValue
)
```

### Input Validation
```kotlin
val validateState = remember(totalBillState.value) {
    totalBillState.value.trim().isNotEmpty()
}
```

## Missing Files 📋

To complete the project, you'll need to provide:
- `InputField.kt` (in components package)
- `RoundIconBtn.kt` (in widgets package)
- `JetTipTheme.kt` (in ui.theme package)
- Utility functions for calculations

## Contributing 🤝

1. Fork the project
2. Create your feature branch (`git checkout -b feature/AmazingFeature`)
3. Commit your changes (`git commit -m 'Add some AmazingFeature'`)
4. Push to the branch (`git push origin feature/AmazingFeature`)
5. Open a Pull Request

## Future Enhancements 🚀

- [ ] Save calculation history
- [ ] Multiple currency support
- [ ] Custom tip presets
- [ ] Dark theme support
- [ ] Export calculations
- [ ] Animations and transitions

## License 📄

This project is licensed under the MIT License - see the LICENSE file for details.

## Contact 📧

BHARGAV VADHIYA bhargavvadhiya23@gmail.com

Project Link: [https://github.com/bhargavvadhiya23/JetTip-App]

---

*Built with ❤️ using Jetpack Compose*
