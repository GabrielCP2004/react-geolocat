import React from 'react';
import { View } from 'react-native';
import ReactNativeForegroundService from "@supersami/rn-foreground-service";
import { registerRootComponent } from 'expo';

ReactNativeForegroundService.register();
class App extends React.Component {
  render() {
    return <View />;
  }
}

registerRootComponent(App);
