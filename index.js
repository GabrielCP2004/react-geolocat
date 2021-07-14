import 'react-native-gesture-handler';
import ReactNativeForegroundService from "@supersami/rn-foreground-service";
import { registerRootComponent } from 'expo';

import App from './App';

ReactNativeForegroundService.register();
ReactNativeForegroundService.start({
  id: 144,
  title: "Foreground Service",
  message: "you are online!",
});
registerRootComponent(App);
