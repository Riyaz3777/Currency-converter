# Currency Converter (Web)

A simple, browser-based currency converter. Pure HTML, CSS, and JavaScript — no build step, no framework, no server needed. Live rates come from the free [Frankfurter API](https://www.frankfurter.app/) (European Central Bank data, no key required).

## Features
- Convert between 14 major currencies
- Live exchange rate, updated as you type
- One-click swap between "from" and "to"
- Works entirely client-side — just open `index.html`

## Run it locally
Just double-click `index.html`, or open it in any browser. No server required.

## Push to GitHub
```bash
cd currency-converter-web
git init
git add .
git commit -m "Initial commit: web-based currency converter"
git branch -M main
git remote add origin https://github.com/<your-username>/<your-repo-name>.git
git push -u origin main
```

## Host it live with GitHub Pages (so it's viewable at a URL)
1. Push the code to GitHub (see above).
2. On GitHub, go to your repo → **Settings** → **Pages**.
3. Under "Build and deployment", set **Source** to `Deploy from a branch`.
4. Choose branch `main` and folder `/ (root)`, then **Save**.
5. Wait a minute or two — GitHub will give you a live URL like:
   `https://<your-username>.github.io/<your-repo-name>/`

That URL is what you can put on your resume as a live demo link.

## Tech Stack
- HTML5, CSS3, vanilla JavaScript
- Frankfurter API (free, no key) for live exchange rates
- Google Fonts (Fraunces + Inter)

## License
Free to use for learning and portfolio purposes.
