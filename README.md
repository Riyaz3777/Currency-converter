# Currency Converter

A simple, browser-based currency converter built with plain **HTML, CSS, and JavaScript** — no frameworks, no build step. Live exchange rates come from the free [Frankfurter API](https://www.frankfurter.app/) (European Central Bank data, no API key needed).

## Project Structure
```
currency-converter-project/
├── index.html   → page structure
├── style.css    → all styling
├── script.js    → conversion logic + API calls
└── README.md
```

## Features
- Convert between 14 major currencies
- Live exchange rate, updates as you type (debounced)
- One-click swap between "from" and "to" currencies
- Clean, responsive UI with light/dark mode support
- Pure client-side — works by just opening `index.html`

## Run it locally
Double-click `index.html`, or open it in any browser. No server, no build tools, nothing to install.

## Push to GitHub
```bash
cd currency-converter-project
git init
git add .
git commit -m "Initial commit: HTML/CSS/JS currency converter"
git branch -M main
git remote add origin https://github.com/<your-username>/<your-repo-name>.git
git push -u origin main
```

## Host it live with GitHub Pages
1. Push the code to GitHub (above).
2. Go to your repo → **Settings** → **Pages**.
3. Under "Build and deployment", set **Source** to `Deploy from a branch`.
4. Choose branch `main`, folder `/ (root)`, then **Save**.
5. Wait a minute or two, then visit:
   `https://<your-username>.github.io/<your-repo-name>/`

That's the live link you can put on your resume.

**Note:** viewing `index.html` on GitHub's own file viewer (`github.com/.../blob/main/index.html`) will only show the code — it won't run the page. You need the GitHub Pages URL above to actually see it working.

## Tech Stack
- HTML5, CSS3, vanilla JavaScript (ES5-compatible)
- Frankfurter API for live exchange rates
- Google Fonts (Fraunces + Inter)

## License
Free to use for learning and portfolio purposes.
