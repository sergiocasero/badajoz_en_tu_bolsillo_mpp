import SwiftUI
import shared

struct MenuRoute: View {
	let greet = Greeting().greet()

	var body: some View {
		Text("Hello from menu route!!")
	}
}

struct ContentView_Previews: PreviewProvider {
	static var previews: some View {
		MenuRoute()
	}
}
