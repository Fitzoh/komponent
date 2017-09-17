const gulp = require('gulp'),
	gulpif = require('gulp-if'),
	HtmlSplitter = require('polymer-build').HtmlSplitter,
	sourcesHtmlSplitter = new HtmlSplitter(),
	babelPresetES2015 = require('babel-preset-es2015'),
	babelPresetES2015NoModules = babelPresetES2015.buildPreset({}, {modules: false}),
	babel = require('gulp-babel')

gulp.task('default', () => {
	return gulp.src([
		'./bower_components/polymer/**/*.html'
	])
		.pipe(sourcesHtmlSplitter.split())
		.pipe(gulpif( /\.js$/, babel({ "presets": [ babelPresetES2015NoModules]}) ) )
		.pipe(sourcesHtmlSplitter.rejoin())
		.pipe(gulp.dest('./bower_components/polymer'))
})