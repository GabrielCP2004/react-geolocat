import React from 'react';
import { SafeAreaView, View } from 'react-native';
import RootScreen from './android/app/src/RootScreen'

export default function App() {
  return (
    <SafeAreaView>
      <View>
        <RootScreen />
      </View>
    </SafeAreaView>
  );
}
